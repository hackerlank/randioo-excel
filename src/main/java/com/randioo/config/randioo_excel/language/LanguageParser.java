package com.randioo.config.randioo_excel.language;

import java.util.HashMap;
import java.util.Map;

import com.randioo.config.randioo_excel.po.ItemConfig;

public abstract class LanguageParser implements LanguageTypeParser, LanguageStatementParser,LanguagePrefix {

	private Map<String, String> declareFormatterMap = new HashMap<>();
	private Map<String, String> assignmentFormatterMap = new HashMap<>();

	public String get(String codeType) {
		String type = null;
		switch (codeType) {
		case "string":
			type = this.getString();
			break;
		case "int":
			type = this.getInt();
			break;
		case "bool":
			type = this.getBoolean();
			break;
		case "short":
			type = this.getShort();
			break;
		case "byte":
			type = this.getByte();
			break;
		case "double":
			type = this.getDouble();
			break;
		}
		return type;
	}

	public String getDeclareFormat(ItemConfig item) {
		String value = this.declareFormatterMap.get(item.type);
		if (value == null) {
			value = this.declareStatementFormatter(item.type);
			this.declareFormatterMap.put(item.type, value);
		}

		return value;
	}

	public String getComment(ItemConfig item) {
		StringBuilder sb = new StringBuilder();
		sb.append(item.comment != null ? item.comment : item.name);
		return sb.toString();
	}

	public String getAssignmentFormat(ItemConfig item) {
		String value = this.assignmentFormatterMap.get(item.type);
		if (value == null) {
			value = this.assignmentFormatter(item.type);
			this.assignmentFormatterMap.put(item.type, value);
		}
		return value;
	}
}
