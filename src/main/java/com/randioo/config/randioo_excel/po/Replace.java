package com.randioo.config.randioo_excel.po;

import java.util.ArrayList;
import java.util.List;

public class Replace {
	public List<Location> locations = new ArrayList<>();

	public Replace(String source) {
		String[] values = source.split("\\|");

		int i = 0;
		while (i < values.length) {
			Location l = new Location();
			l.fileName = values[i++];
			l.page = values[i++];
			l.columnName = values[i++];
			l.value = values[i++];

			locations.add(l);
		}
	}
}
