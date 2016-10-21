package com.randioo.config.randioo_excel;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class NodeConfig {
	public String xlsx;
	public String page;
	public String key;
	public String out;
	public String code;
	public String type;
	public List<ItemConfig> itemList = new ArrayList<>();

	public NodeConfig(Element element) {
		this.type = element.attributeValue("type");
		this.xlsx = element.attributeValue("xlsx");
		this.page = element.attributeValue("page");
		this.key = element.attributeValue("key");
		this.out = element.attributeValue("out");
		this.code = element.attributeValue("code");
		List elements = element.elements("item");
		for (int i = 0; i < elements.size(); i++) {
			itemList.add(new ItemConfig((Element) elements.get(i)));
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return xlsx + " " + page + " " + key + " " + out + " " + code + " " + type
				+ " " + itemList;
	}
}
