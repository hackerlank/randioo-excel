package com.randioo.config.randioo_excel.language.java;

import com.randioo.config.randioo_excel.BasicType;
import com.randioo.config.randioo_excel.Macro;
import com.randioo.config.randioo_excel.language.LanguageParser;
import com.randioo.config.randioo_excel.util.StringUtils;

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
	public String getBool() {
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
		String result = "public ${TYPE} ${CODE};";
		return result.replace(Macro.$TYPE, getLanguageBasicType(valueType));
	}

	@Override
	public String assignmentFormatter(String valueType) {
		StringBuilder sb = new StringBuilder();
		if (valueType.equals(BasicType.STRING)) {
			sb.append("b = new byte[{2}.getShort()];{2}.get(b);{0}.{1}=new String(b);");
		} else {
			sb.append("{0}.{1}={2}.get").append(StringUtils.firstStrToUpperCase(getLanguageBasicType(valueType))).append("();");
		}

		return sb.toString();
	}

	@Override
	public String getCommentFormat() {
		// TODO Auto-generated method stub
		return "/**{0}*/";
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
