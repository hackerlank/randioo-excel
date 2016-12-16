package com.randioo.config.randioo_excel.language.actionscript;

import com.randioo.config.randioo_excel.Constant;
import com.randioo.config.randioo_excel.language.LanguageParser;

public class ActionScriptParser extends LanguageParser {

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
		return "Boolean";
	}

	@Override
	public String getShort() {
		// TODO Auto-generated method stub
		return "int";
	}

	@Override
	public String getByte() {
		// TODO Auto-generated method stub
		return "int";
	}

	@Override
	public String getDouble() {
		// TODO Auto-generated method stub
		return "Number";
	}

	@Override
	public String declareStatementFormatter(String valueType) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("public var {0}:").append(getLanguageBasicType(valueType)).append(";");
		return sb.toString();
	}

	@Override
	public String assignmentFormatter(String valueType) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("{0}.{1}={2}.read");
		switch (valueType) {
		case "string":
			sb.append("UTFBytes("+Constant.BYTES_VAR_NAME+".readUnsignedShort());");
			break;
		case "int":
			sb.append("Int();");
			break;
		case "short":
			sb.append("Short();");
			break;
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
		return ".as";
	}

	@Override
	public String getAssignBrace() {
		// TODO Auto-generated method stub
		return "\n\t\t\t\t";
	}

	@Override
	public String getDeclareBrace() {
		// TODO Auto-generated method stub
		return "\n\t\t";
	}

	@Override
	public String getDataStructureClass(String key) {
		// TODO Auto-generated method stub
		String str = "";
		if (key == null || key.equals("")) {
			str = "Vector.<{0}>";
		} else {
			str = "Dictionary";
		}
		return str;
	}

	@Override
	public String getDataStructureAddMethod(String key) {
		// TODO Auto-generated method stub
		String str = "";
		if (key == null || key.equals("")) {
			str = ".push({1});";
		} else {
			str = "[{1}.{0}]={1};";
		}
		return str;
	}
}
