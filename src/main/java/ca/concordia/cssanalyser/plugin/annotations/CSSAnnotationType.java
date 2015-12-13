package ca.concordia.cssanalyser.plugin.annotations;

public enum CSSAnnotationType {
	
	DUPLICATION("ca.concordia.cssanalyser.plugin.annotations.DuplicatedDeclarations");
	
	private String value;
		
	private CSSAnnotationType(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}

}
