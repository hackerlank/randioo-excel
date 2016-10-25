package com.randioo.config.randioo_excel.language.java;

import com.randioo.config.randioo_excel.language.LanguageParser;

public class JavaParser extends LanguageParser {

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return "String";
	}

	@Override
	public String getInt() {
		// TODO Auto-generated method stub
		return "int";
	}

	@Override
	public String getBoolean() {
		// TODO Auto-generated method stub
		return "boolean";
	}

	@Override
	public String getShort() {
		// TODO Auto-generated method stub
		return "short";
	}

	@Override
	public String getByte() {
		// TODO Auto-generated method stub
		return "byte";
	}

	@Override
	public String getDouble() {
		// TODO Auto-generated method stub
		return "double";
	}

	@Override
	public String declareStatementFormatter(String valueType) {
		StringBuilder sb = new StringBuilder();
		sb.append("public ").append(get(valueType)).append(" {0};");

		return sb.toString();
	}

	@Override
	public String assignmentFormatter(String valueType) {
		StringBuilder sb = new StringBuilder();
		if (valueType.equals("string")) {
			sb.append("b = new byte[{2}.getShort()];{2}.get(b);{0}.{1}=new String(b);");
		} else {
			sb.append("{0}.{1}={2}.get").append(UpStr(get(valueType))).append("();");
		}

		return sb.toString();
	}

	@Override
	public String getCommentFormat() {
		// TODO Auto-generated method stub
		return "/**{0}*/";
	}

	public static String UpStr(String str) {
		return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
	}

	@Override
	public String getPrefix() {
		// TODO Auto-generated method stub
		return ".java";
	}

	@Override
	public String getAssignBrace() {
		// TODO Auto-generated method stub
		return "\n\t\t\t";
	}

	@Override
	public String getDeclareBrace() {
		// TODO Auto-generated method stub
		return "\n\t";
	}

	@Override
	public String getDataStructureClass(String key) {
		// TODO Auto-generated method stub
		String str = "";
		if (key == null || key.equals("")) {
			str = "ArrayList<{0}>";
		} else {
			str = "HashMap<{0}>";
		}
		return str;
	}

	@Override
	public String getDataStructureAddMethod(String key) {
		// TODO Auto-generated method stub
		String str = "";
		if (key == null || key.equals("")) {
			str = ".add({1});";
		} else {
			str = ".put({1}.{0},{1});";
		}
		return str;
	}

}
