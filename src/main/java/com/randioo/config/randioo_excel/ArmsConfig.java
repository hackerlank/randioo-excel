package com.randioo.config.randioo_excel;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ArmsConfig {
	public static final String urlKey = "";
	public int id;
	public String name;

	public static List<ArmsConfig> parse(Data bytes) {
		List<ArmsConfig> dic = new ArrayList<>();
		while (bytes.hasRemaining()) {
			ArmsConfig config = new ArmsConfig();
			config.id = bytes.getInt();
			config.name = bytes.getString();

			dic.add(config);
		}
		return dic;

	}

	public static void main(String[] args) {
		Data data = new Data();
		data.putInt(1);
		data.putString("wcy");

		data.putInt(2);
		data.putString("wcd");

		List<ArmsConfig> list = ArmsConfig.parse(Data.wrap(data.build()));

		System.out.println(list);

		// ArmsConfig.parse(ByteBuffer.wrap(new byte[1024]));
	}
}
