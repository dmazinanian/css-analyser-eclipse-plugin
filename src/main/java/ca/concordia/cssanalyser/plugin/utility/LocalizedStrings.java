package ca.concordia.cssanalyser.plugin.utility;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizedStrings {

	private static final String BUNDLE_NAME = "ca.concordia.cssanalyser.plugin.resources.css-analyser-eclipse-plugin";
	private static ResourceBundle resourceBundle = null;
	
	public enum Keys {
		FIND_DUPLICATED_DECLARATIONS, 
		CLEAR_ANNOTATIONS, 
		EXTRACT_MIXIN, 
		GROUP_SELECTORS, 
		SAVE_FILE_DIALOG_TITLE,
		SAVE_FILE_MESSAGE, 
		CLEAR_RESULTS,
		DETECT_ON_SAVE, 
		INCLUDE_DIFFERENCES,
		PARSE_ERROR_IN_FILE_MESSAGE,
		PARSE_ERROR_IN_FILE_TITLE, 
		DECLARATIONS,
		DECLARATION,
		SELECTORS,
		SELECTOR,
		PROPERTY,
		NO_DUPLICATIONS_FOUND,
		CONSIDER_HAVING_DIFFERENCES,
		NO_DUPLICATIONS_FOUND_TITLE,
		INVALID_MIXIN_NAME,
		MIXIN_DECLARATION,
		STYLED_PROPERTIES,
		MEDIA_QUERY, 
		DECLARATION_TYPE,
		SINGLE_VALUED_DECLARATION, 
		MULTI_VALUED_DECLARATION,
		VIRTUAL_SHORTHAND_DECLARATION,
		SHORTHAND_DECLARATION
	}

	public static void setBundle(Locale locale) {
		try {
			resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
		} catch (Exception e) {
			resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, Locale.US);
		}
	}

	public static String get(Keys key) {
		try {
			return new String(resourceBundle.getString(key.toString()).getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
			return key.toString();
		}
	}

}