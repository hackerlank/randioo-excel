package com.randioo.config.randioo_excel;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConfigBuilder {
	
	private String className;
	private String template;
	public Map<String, String> codeValueMap = new LinkedHashMap<>();

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Map<String, String> getCodeValueMap() {
		return codeValueMap;
	}

	
	
}
