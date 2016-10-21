package com.randioo.config.randioo_excel;

import org.dom4j.Element;

public class ItemConfig {
	public String type;
	public String name;
	public String code;
	public String replace;
	public String comment;

	public ItemConfig(Element element) {
		this.type = element.attributeValue("type");
		this.name = element.attributeValue("name");
		this.code = element.attributeValue("code");
		this.replace = element.attributeValue("replace");
		this.replace = element.attributeValue("comment");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return type + " " + name + " " + code + " " + replace + " " + comment;
	}
}
