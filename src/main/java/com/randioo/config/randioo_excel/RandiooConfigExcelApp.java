package com.randioo.config.randioo_excel;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.randioo.config.randioo_excel.language.LanguageParser;
import com.randioo.config.randioo_excel.language.actionscript.ActionScriptParser;
import com.randioo.config.randioo_excel.language.java.JavaParser;
import com.randioo.config.randioo_excel.po.ConfigParser;
import com.randioo.config.randioo_excel.po.Data;
import com.randioo.config.randioo_excel.po.NodeConfig;
import com.randioo.config.randioo_excel.util.FileUtils;

/**
 * Hello world!
 *
 */
public class RandiooConfigExcelApp {
	public static void main(String[] args) throws Exception {

//		Map<String, String> keyValueMap = new HashMap<>();
//		for (int i = 0; i < args.length; i++) {
//			String[] keyValue = args[i].split("=");
//			keyValueMap.put(keyValue[0], keyValue[1]);
//		}
//
//		if (!check(keyValueMap))
//			return;

		 Constant.CONFIG_URL = "./xml/config.xml";
		 Constant.TEMPLATE_JAVA_URL = "./template/templateJava.txt";
		 Constant.TEMPLATE_AS_URL = "./template/templateAS.txt";
		 Constant.EXCEL_URL = "./excelFile";
		 Constant.OUTPUT_URL = "./out";
		 Constant.PO = "config";
		 Constant.BYTES_VAR_NAME = "data";

//		Constant.CONFIG_URL = keyValueMap.get("config_url");
//		Constant.TEMPLATE_JAVA_URL = keyValueMap.get("java_template_url");
//		Constant.TEMPLATE_AS_URL = keyValueMap.get("as_template_url");
//		Constant.EXCEL_URL = keyValueMap.get("excel_url");
//		Constant.OUTPUT_URL = keyValueMap.get("out_url");
//		Constant.PO = keyValueMap.get("po");
//		Constant.BYTES_VAR_NAME = keyValueMap.get("bytes_varname");

		createCode();

	}

	public static boolean check(Map<String, String> map) {
		boolean flag = true;
		flag = check2(map, "config_url", flag);
		flag = check2(map, "java_template_url", flag);
		flag = check2(map, "as_template_url", flag);
		flag = check2(map, "excel_url", flag);
		flag = check2(map, "out_url", flag);
		flag = check2(map, "po", flag);
		flag = check2(map, "bytes_varname", flag);

		return flag;
	}

	private static boolean check2(Map<String, String> map, String key, boolean flag) {
		if (map.get(key) == null) {
			System.out.println("need " + key);
			return false;
		}
		if (!flag)
			return false;

		return true;

	}

	public static void createCode() throws IOException, DocumentException {
		String templateJavaStr = FileUtils.readContent(Constant.TEMPLATE_JAVA_URL);
		String templateASStr = FileUtils.readContent(Constant.TEMPLATE_AS_URL);

		SAXReader sax = new SAXReader();
		// Step 1 目录
		Document doc = sax.read(Constant.CONFIG_URL);
		Element root = doc.getRootElement();

		JavaParser javaParser = new JavaParser();
		ActionScriptParser asParser = new ActionScriptParser();
		Iterator<Element> nodeIt = root.elementIterator();
		while (nodeIt.hasNext()) {
			NodeConfig node = new NodeConfig(nodeIt.next());
			ConfigParser parser = new ConfigParser(node, Constant.EXCEL_URL);

			Data data = parser.getData();
			FileUtils.writeData(Constant.OUTPUT_URL, node.out, data.build());

			createLanguageCode(templateJavaStr, javaParser, node, parser);
			createLanguageCode(templateASStr, asParser, node, parser);
		}
	}

	private static void createLanguageCode(String templateJavaStr, LanguageParser languageParser, NodeConfig node,
			ConfigParser parser) throws IOException {
		String assign = parser.parseAssignment(Constant.PO, Constant.BYTES_VAR_NAME, languageParser,
				languageParser.getAssignBrace());
		String declare = parser.parseDeclare(languageParser, languageParser.getDeclareBrace());

		String result = templateJavaStr.replace("$URL", node.out).replace("$PROP", declare)
				.replace("$CLASS_NAME", node.className).replace("$ASSIGN", assign).replace("$PO", Constant.PO)
				.replace("$BYTES_VAR_NAME", Constant.BYTES_VAR_NAME);

		File file = FileUtils.createFile(Constant.OUTPUT_URL, node.code + languageParser.getPrefix());
		FileUtils.writeFile(Constant.OUTPUT_URL + FileUtils.fileSplit + file.getName(), result);
	}

}
