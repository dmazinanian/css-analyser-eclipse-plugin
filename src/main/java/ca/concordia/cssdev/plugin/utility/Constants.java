package ca.concordia.cssdev.plugin.utility;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public interface Constants {
	
	public static final Color SELECTOR_COLOR = new Color(Display.getCurrent(), new RGB(0, 0, 0));
	public static final Color SELECTOR_BG_COLOR = new Color(Display.getCurrent(), new RGB(235, 235, 235));
	public static final Color SELECTOR_BORDER_COLOR = new Color(Display.getCurrent(), new RGB(80, 80, 80));
	public static final Color CASCADING_DEPENDENCY_COLOR = new Color(Display.getCurrent(), new RGB(255, 50, 50));
	public static final Color SPECIFICITY_DEPENDENCY_COLOR = new Color(Display.getCurrent(), new RGB(50, 50, 255));
	public static final Color IMPORTANCE_DEPENDENCY_COLOR = new Color(Display.getCurrent(), new RGB(255, 50, 255));
	public static final Color DEPENDENCY_VISUALIZATION_LEGEND_BG_COLOR = new Color(Display.getCurrent(), new RGB(240, 240, 240));
	public static final Color DEPENDENCY_VISUALIZATION_LEGEND_BORDER_COLOR = DEPENDENCY_VISUALIZATION_LEGEND_BG_COLOR;
	public static final Color DEPENDENCY_VISUALIZATION_LEGEND_FORECOLOR = new Color(Display.getCurrent(), new RGB(0, 0, 0));
	public static final Color MEDIA_QEURY_DEPENDENCY_COLOR = new Color(Display.getCurrent(), new RGB(50, 50, 50));
	public static final Color SELECTED_SELECTOR_COLOR = new Color(Display.getCurrent(), new RGB(255, 100, 100));
	public static final Color HIGHLIGHTED_TEXT = new Color(Display.getDefault(), new RGB(100, 100, 100));
	
	public static final int DEPENDENCY_CONNECTIONS_ALPHA_DESABLED = 50;
	public static final int DEPENDENCY_CONNECTIONS_ALPHA_INITIAL = 150;
	public static final int DEPENDENCY_CONNECTIONS_ALPHA_HOVERED = 255;
	public static final int DEPENDENCY_CONNECTIONS_ALPHA_SELECTED = 255;
	public static final int DEPENDENCY_CONNECTIONS_WIDTH_INITIAL = 1;
	public static final int DEPENDENCY_CONNECTIONS_WIDTH_HOVERED = 1;
	public static final int DEPENDENCY_CONNECTIONS_WIDTH_SELECTED = 2;
	
	public static final String ICON_PATH = "icons";
	public static final String SEARCH_ICON = ICON_PATH + "/" + "find_obj.gif";
	public static final String DOM_ICON = ICON_PATH + "/" + "html_tag_obj.gif";
	public static final String HTML_FILE_ICON = ICON_PATH + "/" + "html.gif";
	public static final String CSS_PROPERTY_ICON = ICON_PATH + "/" + "css_propertyname.gif";
	public static final String CSS_SELECTOR_ICON = ICON_PATH + "/" + "tag_selector.gif";
	public static final String OVERRIDDEN_OVERLAY = ICON_PATH + "/" + "overridden_overlay.png";
	public static final String OVERRIDING_OVERLAY = ICON_PATH + "/" + "overriding_overlay.png";
	public static final String FILTER_ICON = ICON_PATH + "/" + "filter.png";
	public static final String CLEAR_FILTERS_ICON = ICON_PATH + "/" + "clear_filters.png";;
	
}
