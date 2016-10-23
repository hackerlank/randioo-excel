package com.randioo.config.randioo_excel.po;

import org.dom4j.Element;

public class ItemConfig {
	public final String type;
	public final String name;
	public final String code;
	public final String comment;
	public final String replace;

	public ItemConfig(Element element) {
		this.type = element.attributeValue("type");
		this.name = element.attributeValue("name");
		this.code = element.attributeValue("code");
		this.replace = element.attributeValue("replace");
		this.comment = element.attributeValue("comment");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return type + " " + name + " " + code + " " + replace + " " + comment;
	}
}
