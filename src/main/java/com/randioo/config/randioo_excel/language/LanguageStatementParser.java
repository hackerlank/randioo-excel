package com.randioo.config.randioo_excel.language;

public interface LanguageStatementParser {
	public String declareStatementFormatter(String valueType);

	public String assignmentFormatter(String valueType);

	public String getCommentFormat();

	public String getDeclareBrace();

	public String getAssignBrace();
	
	public String getDataStructureClass(String key);
	
	public String getDataStructureAddMethod(String key);
}
