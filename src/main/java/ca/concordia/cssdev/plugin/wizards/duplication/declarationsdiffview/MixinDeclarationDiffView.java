package ca.concordia.cssdev.plugin.wizards.duplication.declarationsdiffview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import ca.concordia.cssanalyser.csshelper.CSSPropertyCategoryHelper;
import ca.concordia.cssanalyser.cssmodel.declaration.Declaration;
import ca.concordia.cssanalyser.cssmodel.declaration.PropertyAndLayer;
import ca.concordia.cssanalyser.cssmodel.declaration.value.DeclarationValue;
import ca.concordia.cssanalyser.cssmodel.selectors.Selector;
import ca.concordia.cssanalyser.migration.topreprocessors.mixin.MixinDeclaration;
import ca.concordia.cssanalyser.migration.topreprocessors.mixin.MixinLiteral;
import ca.concordia.cssanalyser.migration.topreprocessors.mixin.MixinParameter;
import ca.concordia.cssanalyser.migration.topreprocessors.mixin.MixinValue;
import ca.concordia.cssdev.plugin.utility.PreferencesUtil;
import ca.concordia.cssdev.plugin.utility.PreferencesUtil.ColorConstants;
import ca.concordia.cssdev.plugin.wizards.duplication.declarationsdiffview.ExtendedCheckBox.ExtendedCheckboxSelectionListener;

public class MixinDeclarationDiffView extends Composite {
	
	static final Color BACKGROUND_COLOR = PreferencesUtil.getTextEditorBackgroundColor();
	static final Color DIFFERENCE_BACKGROUND_COLOR = new Color(Display.getCurrent(), 255, 250, 200);
	static final Color SELECTOR_COLOR = PreferencesUtil.getCSSColor(ColorConstants.SELECTOR);
	static final Color PROPERTY_FOREGROUND_COLOR = PreferencesUtil.getCSSColor(ColorConstants.PROPERY);
	static final Color LITERAL_COLOR = PreferencesUtil.getCSSColor(ColorConstants.LITERAL);
	static final Color MEDIA_COLOR = PreferencesUtil.getCSSColor(ColorConstants.MEDIA);
	static final Color NORMAL_TEXT_COLOR = new Color(Display.getCurrent(), 0, 0, 0);
	static final Color DISABLED_LABEL_COLOR = new Color(Display.getCurrent(), 150, 150, 150);
	
	private final MixinDeclaration mixinDeclaration;
	
	private final List<MixinDeclarationSelectionListener> mixinDeclarationSelectionListeners;
	
	private final Map<Selector, PropertyLabel> selectorToPropertyLabelsMap;
	private final List<ValueLabel> mixinParametersLabels;
	
	private PropertyCheckBox mixinPropertyCheckBox;
	
	public MixinDeclarationDiffView(Composite parent, MixinDeclaration mixinDeclaration) {
		super(parent, SWT.NONE);
		this.mixinDeclaration = mixinDeclaration;
		this.mixinDeclarationSelectionListeners = new ArrayList<>();
		this.mixinParametersLabels = new ArrayList<>();
		this.selectorToPropertyLabelsMap = new HashMap<>();
		layoutComposite();
		createMixinDeclarationArea();	
	}

	private void layoutComposite() {
		this.setBackground(BACKGROUND_COLOR);
		GridLayout layout = new GridLayout(mixinDeclaration.getAllSetPropertyAndLayers().size() + 2, false);
		layout.horizontalSpacing = 8;
		layout.verticalSpacing = 0;
		this.setLayout(layout);
	}
	
	private void createMixinDeclarationArea() {
		String nonVendorNonHackedProperty = Declaration.getNonVendorProperty(Declaration.getNonHackedProperty(mixinDeclaration.getPropertyName()));
		Composite categoryComposite = new PropertyCategoryMarker(this, CSSPropertyCategoryHelper.getCSSCategoryOfProperty(nonVendorNonHackedProperty));
		GridData categoryGridData = new GridData();
		categoryGridData.widthHint = 19;
		categoryGridData.heightHint = 19;
		categoryComposite.setLayoutData(categoryGridData);
		categoryComposite.setBackground(BACKGROUND_COLOR);
		
		mixinPropertyCheckBox = new PropertyCheckBox(this, mixinDeclaration.getPropertyName(), true);
		new ExtractMixinMixinPropertyToolTip(mixinPropertyCheckBox.getPropertyLabel().getPropertyLabel().getUnderlayingLabel(), mixinDeclaration);
		mixinPropertyCheckBox.setBackground(BACKGROUND_COLOR);
				
		for (MixinValue value : mixinDeclaration.getMixinValues()) {
			Color valueColor = NORMAL_TEXT_COLOR;
			if (value instanceof MixinLiteral)
				valueColor = LITERAL_COLOR;
			ValueLabel mixinDeclarationValueLabel = new MixinDeclarationValueLabel(this, valueColor, true, value);
			new ExtractMixinMixinValueToolTip(mixinDeclarationValueLabel.getUnderlayingLabel(), mixinDeclaration, value);
			mixinDeclarationValueLabel.setBackground(BACKGROUND_COLOR);
			mixinPropertyCheckBox.addDeclarationValueLabel(mixinDeclarationValueLabel);
			if (value instanceof MixinParameter) {
				mixinParametersLabels.add(mixinDeclarationValueLabel);
			}
		}
		
		mixinPropertyCheckBox.addCheckBoxSelectionListener(new ExtendedCheckboxSelectionListener() {
			@Override
			public void selectionChanged(Object source, boolean selected) {
				for (PropertyLabel propertyLabel : selectorToPropertyLabelsMap.values()) {
					propertyLabel.setLabelEnabled(selected);
				}
				for (MixinDeclarationSelectionListener mixinDeclarationSelectionListener : mixinDeclarationSelectionListeners) {
					mixinDeclarationSelectionListener.mixinDeclarationSelectionChanged(mixinDeclaration, selected);
				}
			}
		});
		
		for (Declaration declaration : mixinDeclaration.getForDeclarations()) {
			PropertyLabel declarationPropertyLabel = new PropertyLabel(this, declaration.getProperty(), false);
			declarationPropertyLabel.setBackground(BACKGROUND_COLOR);
			new ExtractMixinDeclarationPropertyTooltip(declarationPropertyLabel.getObjectForTooltip(), declaration);
			selectorToPropertyLabelsMap.put(declaration.getSelector(), declarationPropertyLabel);
			GridData layoutData = new GridData();
			layoutData.horizontalIndent = 45;
			layoutData.horizontalSpan = 2;
			declarationPropertyLabel.setLayoutData(layoutData);
			Set<PropertyAndLayer> checkedPropertiesAndLayers = new HashSet<>();
			for (DeclarationValue declarationValue : mixinDeclaration.getReferenceDeclaration().getDeclarationValues()) {
				PropertyAndLayer propertyAndLayer = declarationValue.getCorrespondingStylePropertyAndLayer();
				if (propertyAndLayer != null && !checkedPropertiesAndLayers.contains(propertyAndLayer)) {
					Collection<DeclarationValue> values = declaration.getDeclarationValuesForStyleProperty(propertyAndLayer); 
					if (values == null)
						values = new ArrayList<>();
					ValueLabel valueLabel = new DeclarationValueLabel(this, LITERAL_COLOR, false, values);
					if (mixinDeclaration.getMixinValueForPropertyandLayer(propertyAndLayer) instanceof MixinParameter) {
						valueLabel.setBackground(DIFFERENCE_BACKGROUND_COLOR);	
					} else {
						valueLabel.setBackground(BACKGROUND_COLOR);	
					}

					new ExtractMixinDeclarationValueTooltip(valueLabel.getUnderlayingLabel(), declaration, values);
					declarationPropertyLabel.addDeclarationValueLabel(valueLabel);
					checkedPropertiesAndLayers.add(propertyAndLayer);
				}
			}
		}
		setMixinDeclarationSelected(true);
	}

	public MixinDeclaration getMixinDeclaration() {
		return mixinDeclaration;
	}
	
	public interface MixinDeclarationSelectionListener {
		void mixinDeclarationSelectionChanged(MixinDeclaration mixinDeclaration, boolean selected);
	}

	public void addMixinDeclarationSelectionListener(MixinDeclarationSelectionListener mixinDeclarationSelectionListener) {
		this.mixinDeclarationSelectionListeners.add(mixinDeclarationSelectionListener);
	}

	public void updateParameterNames() {
		for (ValueLabel declarationValueLabel : mixinParametersLabels) {
			declarationValueLabel.updateLabel();
		}
	}

	public String getProperty() {
		return mixinDeclaration.getPropertyName();
	}

	public boolean isMixinDeclarationSelected() {
		return mixinPropertyCheckBox.getSelection();
	}

	public void setMixinDeclarationSelected(boolean selected) {
		mixinPropertyCheckBox.setSelection(selected);		
	}

	public void setDeclarationSelected(Selector selector, boolean selected) {
		if (selectorToPropertyLabelsMap.containsKey(selector)) {
			selectorToPropertyLabelsMap.get(selector).setLabelEnabled(selected);
		}
	}
	
}
