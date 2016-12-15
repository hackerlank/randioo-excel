package com.randioo.config.randioo_excel.language;

import java.util.HashMap;
import java.util.Map;

import com.randioo.config.randioo_excel.po.ItemConfig;

public abstract class LanguageParser implements LanguageTypeParser, LanguageStatementParser,LanguagePrefix {

	//声明类型的缓存池
	private Map<String, String> declareFormatterMap = new HashMap<>();
	//声明获取值的缓存池
	private Map<String, String> assignmentFormatterMap = new HashMap<>();

	/**
	 * 根据配置表中的类型获得对应语言的类型
	 * @param codeType
	 * @return
	 */
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

	/**
	 * 根据类型获得声明语句
	 * @param item
	 * @return
	 */
	public String getDeclareFormat(ItemConfig item) {
		//从缓存中尝试查找语句
		String value = this.declareFormatterMap.get(item.type);
		//如果没有
		if (value == null) {
			//根据语言规则进行生成
			value = this.declareStatementFormatter(item.type);
			//加入缓存中
			this.declareFormatterMap.put(item.type, value);
		}

		return value;
	}

	/**
	 * 根据某一项属性获得注释
	 * @param item
	 * @return
	 */
	public String getComment(ItemConfig item) {
		
		//如果没有填注释选项，则默认使用属性名称
		return item.comment != null ? item.comment : item.name;		
	}

	/**
	 * 返回每种语言对于类型的获取值的方式
	 * @param item
	 * @return
	 */
	public String getAssignmentFormat(ItemConfig item) {
		//从缓存中尝试查找语句
		String value = this.assignmentFormatterMap.get(item.type);
		//如果没有
		if (value == null) {
			//根据语言规则进行生成
			value = this.assignmentFormatter(item.type);
			//加入缓存中
			this.assignmentFormatterMap.put(item.type, value);
		}
		return value;
	}
}
