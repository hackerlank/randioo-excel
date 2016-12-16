package com.randioo.config.randioo_excel.po;

import org.dom4j.Element;
/**
 * 类的属性值
 * @author wcy 2016年12月16日
 *
 */
public class FieldConfig {
	public final String type;
	public final String name;
	public final String code;
	public final String comment;
	public final String replace;

	public FieldConfig(Element element) {
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
