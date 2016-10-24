package com.randioo.config.randioo_excel.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.randioo.config.randioo_excel.po.Data;

public class FileUtils {
	public static final String fileSplit = System.getProperty("file.separator");

	public static File createFile(String path, String fileName) {
		File checkFile = new File(path + fileSplit + fileName);
		if (checkFile.exists())
			return checkFile;
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}

		File file = new File(f, fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
				return file;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String readContent(String path) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			StringBuilder sb = new StringBuilder();
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			br.close();

			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public static byte[] readBytes(String path) throws IOException {
		File f = new File(path);
		@SuppressWarnings("resource")
		FileInputStream fis = new FileInputStream(f);
		List<Byte> list = new ArrayList<>();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer, 0, buffer.length)) != -1) {
			for (int i = 0; i < len; i++) {
				list.add(buffer[i]);
			}
		}
		byte[] result = new byte[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = list.get(i);
		}
		return result;
	}

	public static void writeFile(String path, String code) throws IOException {
		BufferedWriter br = new BufferedWriter(new FileWriter(path));
		br.write(code);
		br.flush(); // 刷新缓冲区的数据到文件
		br.close();
	}

	public static void writeData(String path, String fileName, byte[] buffer) throws IOException {
		File file = FileUtils.createFile(path, fileName);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(buffer);
		fos.close();
	}
}
