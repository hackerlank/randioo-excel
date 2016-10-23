package com.randioo.config.randioo_excel.po;

public class ReplaceLocation {
	public final String fileName;
	public final String page;
	public final String columnName;
	public final String value;

	public ReplaceLocation(String source) {
		String[] values = source.split("\\|");

		fileName = values[0];
		page = values[1];
		columnName = values[2];
		value = values[3];
	}
}
