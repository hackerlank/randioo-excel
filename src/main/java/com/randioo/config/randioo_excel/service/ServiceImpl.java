package com.randioo.config.randioo_excel.service;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.randioo.config.randioo_excel.Constant;
import com.randioo.config.randioo_excel.language.LanguageParser;
import com.randioo.config.randioo_excel.language.actionscript.ActionScriptParser;
import com.randioo.config.randioo_excel.language.java.JavaParser;
import com.randioo.config.randioo_excel.po.ConfigParser;
import com.randioo.config.randioo_excel.po.Data;
import com.randioo.config.randioo_excel.po.NodeConfig;
import com.randioo.config.randioo_excel.util.FileUtils;


public class ServiceImpl implements Service{
	public void createCode() throws IOException, DocumentException {
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
			System.out.println(node.className);
			ConfigParser parser = new ConfigParser(node, Constant.EXCEL_URL);

			Data data = parser.getData();
			FileUtils.writeData(Constant.OUTPUT_URL, node.out, data.build());

			createLanguageCode(templateJavaStr, javaParser, node, parser);
			createLanguageCode(templateASStr, asParser, node, parser);
		}
	}

	private void createLanguageCode(String templateJavaStr, LanguageParser languageParser, NodeConfig node,
			ConfigParser parser) throws IOException {
		String assign = parser.parseAssignment(Constant.PO, Constant.BYTES_VAR_NAME, languageParser,
				languageParser.getAssignBrace());
		String declare = parser.parseDeclare(languageParser, languageParser.getDeclareBrace());
		String dicType = parser.parseDataStructure(languageParser);
		String dicAddMethod = parser.parseDataStructureAddMethod(languageParser, Constant.PO);

		String result = templateJavaStr.replace("$URL", node.out).replace("$PROP", declare)
				.replace("$CLASS_NAME", node.className).replace("$ASSIGN", assign).replace("$PO", Constant.PO)
				.replace("$BYTES_VAR_NAME", Constant.BYTES_VAR_NAME).replace("$DIC_TYPE", dicType)
				.replace("$DIC_ADD", dicAddMethod);

		File file = FileUtils.createFile(Constant.OUTPUT_URL, node.code + languageParser.getPrefix());
		FileUtils.writeFile(Constant.OUTPUT_URL + FileUtils.fileSplit + file.getName(), result);
	}

}
