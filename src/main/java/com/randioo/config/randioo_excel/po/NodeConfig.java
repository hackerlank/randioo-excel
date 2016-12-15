package com.randioo.config.randioo_excel.po;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

/**
 * 每个节点进行实例化
 * @author wcy 2016年12月15日
 *
 */
public class NodeConfig {
	public final String xlsx;
	public final String page;
	public final String key;
	public final String out;
	public final String code;
	public final String type;
	public final String className;
	public final List<ItemConfig> itemList = new ArrayList<>();

	public NodeConfig(Element element) {
		this.type = element.attributeValue("type");
		this.xlsx = element.attributeValue("xlsx");
		this.page = element.attributeValue("page");
		this.key = element.attributeValue("key");
		this.out = element.attributeValue("out");
		this.code = element.attributeValue("code");
		List<?> elements = element.elements("item");
		for (int i = 0; i < elements.size(); i++) {
			itemList.add(new ItemConfig((Element) elements.get(i)));
		}
		this.className = code.split("\\.")[0];
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return xlsx + " " + page + " " + key + " " + out + " " + code + " " + type
				+ " " + itemList;
	}
}
