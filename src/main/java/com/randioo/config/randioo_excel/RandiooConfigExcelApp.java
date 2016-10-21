package com.randioo.config.randioo_excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
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
		// System.out.println("Hello World!");
		//
		SAXReader sax = new SAXReader();
		// Step 1 目录
		Document doc = sax.read("./xml/config.xml");
		Element root = doc.getRootElement();

		Iterator<Element> nodeIt = root.elementIterator();
		while (nodeIt.hasNext()) {
			NodeConfig node = new NodeConfig(nodeIt.next());
			File file = new File("./excelFile/" + node.xlsx);
			Sheet sheet = getSheet(file, node.page);
			Iterator<Row> rowIt = sheet.rowIterator();
			List<ItemConfig> itemConfigList = node.itemList;

			List<String> propList1 = new ArrayList<>();
			List<String> readerList1 = new ArrayList<>();
			List<String> propList2 = new ArrayList<>();
			List<String> readerList2 = new ArrayList<>();
			createCode(itemConfigList, propList1, readerList1, "java");
			createCode(itemConfigList, propList2, readerList2, "as");
			System.out.println(Prop(propList1, "\n\t\t"));
			System.out.println(Prop(propList2, "\n\t\t"));

			loadData(rowIt, itemConfigList);

		}

		// StringBuilder temp = new StringBuilder();
		// String r = "\n";
		// String tab = "\t";
		//
		// temp.append("public class $ClassName{").append(r);
		// temp.append(tab).append("public static final String urlKey=$SourceUrl;").append(r);
		// temp.append(tab).append("$Attribute").append(r);
		// temp.append(tab).append("public static $DicType parse(Data bytes){").append(r);
		// temp.append(tab).append(tab).append("$DicType dic = new $DicTypeImpl();").append(r);
		// temp.append(tab).append(tab).append("while(bytes.hasRemaining()){").append(r);
		// temp.append(tab).append(tab).append(tab).append("$Result").append(r);
		// temp.append(tab).append(tab).append(tab).append("$putConfig").append(r);
		// temp.append(tab).append(tab).append("}").append(r);
		// temp.append(tab).append(tab).append("reutrn dic;").append(r);
		// temp.append(tab).append("}").append(r);
		// temp.append("}");
		// String template = temp.toString();
		// String result = template.replace("$Attribute", "bb").replace("$Key",
		// "ccc");

		// System.out.println(template);

	}

	private static String Prop(List<String> codes, String format) {
		StringBuilder sb = new StringBuilder();
		for (String code : codes) {
			sb.append(code).append(format);
		}
		return sb.toString();
	}

	private static void createCode(List<ItemConfig> itemConfigList, List<String> propList, List<String> readerList,
			String codeType) {
		// TODO Auto-generated method stub
		for (ItemConfig config : itemConfigList) {
			String propCode = null;
			String readerCode = null;
			if (codeType.equals("java")) {
				propCode = String.format("public %s %s;", javaType(config.type), config.code);
				readerCode = String.format("vo.%s = bytes.get%s();", config.code, UpStr(javaType(config.type)));
			} else if (codeType.equals("as")) {
				propCode = String.format("public var %s:%s;", config.code, asType(config.type));
			}
			propList.add(propCode);
		}
	}

	public static String UpStr(String str) {
		return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
	}

	public static String javaType(String type) {
		String javaTypeCode = null;
		switch (type) {
		case "string":
			javaTypeCode = "String";
			break;
		case "int":
			javaTypeCode = "int";
			break;
		case "bool":
			javaTypeCode = "boolean";
			break;
		case "short":
			javaTypeCode = "short";
			break;
		case "byte":
			javaTypeCode = "byte";
			break;
		case "double":
			javaTypeCode = "double";
			break;
		}
		return javaTypeCode;
	}

	public static String asType(String type) {
		String asTypeCode = null;
		switch (type) {
		case "string":
			asTypeCode = "String";
			break;
		case "int":
			asTypeCode = "int";
			break;
		case "bool":
			asTypeCode = "boolean";
			break;
		case "short":
			asTypeCode = "int";
			break;
		case "byte":
			asTypeCode = "int";
			break;
		case "double":
			asTypeCode = "Number";
			break;
		}
		return asTypeCode;
	}

	private static void loadData(Iterator<Row> rowIt, List<ItemConfig> itemConfigList) {
		Map<String, Integer> nameColumnIndexMap = initColumns(rowIt.next());
		Data data = new Data();
		while (rowIt.hasNext()) {
			Row row = rowIt.next();
			String value = row.getCell(0).getStringCellValue();
			// 查看是否有下行
			if (value == null || value.equals(""))
				break;

			for (int i = 0; i < itemConfigList.size(); i++) {
				ItemConfig itemConfig = itemConfigList.get(i);
				pushData(data, itemConfig.type, row.getCell(nameColumnIndexMap.get(itemConfig.name)));
			}

		}
	}

	public static void pushData(Data data, String type, Cell cell) {
		switch (type) {
		case "int": {
			int value = cell.getCellType() == Cell.CELL_TYPE_STRING ? Integer.parseInt(cell.getStringCellValue()) : (int) cell
					.getNumericCellValue();
			data.putInt(value);
		}

			break;
		case "string": {
			String value = cell.getCellType() == Cell.CELL_TYPE_NUMERIC ? cell.getNumericCellValue() + "" : cell
					.getStringCellValue();
			data.putString(value);
		}

			break;
		case "short": {
			short value = cell.getCellType() == Cell.CELL_TYPE_STRING ? Short.parseShort(cell.getStringCellValue()) : (short) cell
					.getNumericCellValue();
			data.putShort(value);
		}

			break;
		case "bool": {
			int value = cell.getCellType() == Cell.CELL_TYPE_STRING ? Integer.parseInt(cell.getStringCellValue()) : (int) cell
					.getNumericCellValue();
			boolean v = value == 1;
			data.putBoolean(v);
		}
			break;
		case "double": {
			double value = cell.getCellType() == Cell.CELL_TYPE_NUMERIC ? cell.getNumericCellValue() : Double
					.parseDouble(cell.getStringCellValue());
			data.putDouble(value);
		}
			break;
		case "byte": {
			byte v = Byte
					.parseByte(cell.getCellType() == Cell.CELL_TYPE_NUMERIC ? cell.getNumericCellValue() + "" : cell
							.getStringCellValue());
			data.putByte(v);
		}

			break;
		}
	}

	/**
	 * 列明初始化
	 * 
	 * @param sheet
	 * @return
	 * @author wcy 2016年10月21日
	 */
	private static Map<String, Integer> initColumns(Row row) {
		Iterator<Cell> cells = row.cellIterator();
		Map<String, Integer> nameMap = new HashMap<>();
		while (cells.hasNext()) {
			Cell cell = cells.next();
			String str = cell.getStringCellValue();
			if (str == null || str.equals("")) {
				break;
			}

			nameMap.put(str, cell.getColumnIndex());
		}
		return nameMap;
	}

	public static Sheet getSheet(File f, String page) throws Exception {
		FileInputStream fis = new FileInputStream(f);
		Workbook workbook = new XSSFWorkbook(fis);
		return workbook.getSheet(page);
	}
}
