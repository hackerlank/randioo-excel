package com.randioo.config.randioo_excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Hello world!
 *
 */
public class RandiooConfigExcelApp {
	public static void main(String[] args) throws Exception {
		System.out.println("Hello World!");

		SAXReader sax = new SAXReader();
		// Step 1 目录
		Document doc = sax.read("./xml/config.xml");
		Element root = doc.getRootElement();

		Iterator<Element> nodeIt = root.elementIterator();
		while (nodeIt.hasNext()) {
			Element nodeE = nodeIt.next();
			String xlsxName = nodeE.attributeValue("xlsx");
			String page = nodeE.attributeValue("page");
			String out = nodeE.attributeValue("out");
			String code = nodeE.attributeValue("code");
			Sheet sheet = getSheet("excelFile", xlsxName, page);
			
		}

	}

	public static Sheet getSheet(String path, String xlsxName, String sheetName) throws Exception {
		File f = new File(new StringBuilder(String.valueOf(System.getProperty("user.dir")))
				.append(System.getProperty("file.separator")).append(path).append(System.getProperty("file.separator"))
				.append(xlsxName).toString());
		FileInputStream fis = new FileInputStream(f);
		Workbook workbook = new XSSFWorkbook(fis);
		return workbook.getSheet(sheetName);
	}
}
