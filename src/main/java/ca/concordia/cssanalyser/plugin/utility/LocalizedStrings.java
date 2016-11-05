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
		DECLARATION_VALUE,
		SELECTORS,
		SELECTOR,
		PROPERTY,
		NO_DUPLICATIONS_FOUND,
		CONSIDER_HAVING_DIFFERENCES,
		NO_DUPLICATIONS_FOUND_TITLE,
		INVALID_MIXIN_NAME,
		MIXIN_DECLARATION,
		MIXIN_LITERAL,
		MIXIN_PARAMETER,
		STYLED_PROPERTIES,
		MEDIA_QUERY, 
		DECLARATION_TYPE,
		SINGLE_VALUED_DECLARATION, 
		MULTI_VALUED_DECLARATION,
		VIRTUAL_SHORTHAND_DECLARATION,
		SHORTHAND_DECLARATION,
		MIXIN_VALUE_TYPE,
		MIXIN_VALUE_NAME,
		PROPERTY_LAYER,
		CREATING_CHANGE,
		REMOVE_DUPLICATED_DECLARATIONS,
		ADD_GROUPING_SELECTOR,
		GROUP_DECLARATIONS_IN_SELECTORS,
		ADD_NECESSARY_DECLARATIONS,
		ADD_MIXIN_DECLARATION,
		ADD_MIXIN_CALL,
		ADD_MIXIN_CALL_REORDER_DECLARTIONS,
		EXTRACT_MIXIN_FROM_DECLARATIONS_IN_SELECTORS,
		BREAK_PRESENTATION_ERROR,
		MIXIN_NAME,
		EMPTY_PROPERTIES,
		PROPERTIES_TO_INCLUDE_IN_MIXIN,
		MIXIN_PARAMETERS,
		INVALID_MIXIN_PARAMETER_NAME,
		DUPLICATE_PARAMETER_NAME, 
		EMPTY_SELECTORS,
		NEW_MIXIN_EXTRACTED_FROM,
		NUMBER_OF_OPPORTUNITIES,
		PARSING_CSS_FILE,
		FINDING_DUPLICATIONS,
		GETTING_OPPORTUNITIES,
		POPULATING_VIEW,
		GETTING_INFO,
		FINDING_REFACTORING_OPPORTUNITIES,
		CREATING_REMOVE_DECLARATIONS_CHANGE,
		CREATING_ADD_DECLARATIONS_CHANGE,
		CREATING_ADD_MIXIN_CHANGE,
		CREATING_ADD_MIXIN_CALLS_CHANGE,
		CHECKING_PRECONDITIONS, 
		SHOW_ANALYSIS_SETTINGS,
		ANALYSIS_OPTIONS,
		SHOULD_ANALYZE_DOM,
		YES,
		NO,
		URL, 
		RANDOM_DATA_IN_FORMS, 
		CLICK_ONCE,
		CLICK_RANDOMLY,
		CLICK_ELEMENTS,
		DONT_CLICK_ELEMENTS,
		DONT_CLICK_ELEMENTS_CHILDREN_OF,
		CLICK_DEFAULT_ELEMENTS,
		CRAWL_FRAMES,
		OUTPUT_DIRECTORY,
		TEMP_OUT_FOLDER,
		SPECIFY,
		MAX_DEPTH,
		MAX_STATES,
		WAIT_TIME_AFTER_RELOAD,
		WAIT_TIME_AFTER_EVENT,
		CRAWL_HIDDEN_ANCHORS,
		REFRESEH_DEPENDENCIES,
		CRAWLING_JOB,
		DUPLICATED, 
		REORDER_SELECTORS,
		VISUALIZE_DEPENDENCIES,
		CASCADING_DEPENDENCY,
		SPECIFICITY_DEPENDENCY, 
		GETTING_DEPENDENCIES,
		GENERATING_DEPENDENCY_VISUALIZATION,
		REFACTORING_NOT_APPLICABLE_DUE_TO_DEPENDENCIES,
		RESET_ZOOM,
		OVERRIDDEN_PROPERTIES,
		IMPORTANCE_DEPENDENCY,
		MEDIA_QUERY_DEPENDENCY,
		SELECTOR_SEARCH,
		AFFECTED_DOM_NODES,
		GOTO_DEFINITION,
		GOTO_OVERRIDING_SELECTOR,
		GOTO_OVERRIDDEN_SELECTOR,
		DUPLICATION_TYPES,
		PROPERTY_CATEGORIES, 
		NUMBER_OF_DECLARATIONS,
		NUMBER_OF_SELECTORS
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
