package com.randioo.config.randioo_excel;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.randioo.config.randioo_excel.po.ClassConfig;
import com.randioo.config.randioo_excel.po.Data;
import com.randioo.config.randioo_excel.util.FileUtils;

/**
 * Hello world!
 *
 */
public class RandiooConfigExcelApp {
	public static void main(String[] args) throws Exception {
		LangScriptEngine engine = new LangScriptEngine();
		DataCreator dataCreator = new DataCreator();
		SAXReader sax = new SAXReader();

		Constant.TARGET = args.length == 0 ? "java" : args[0];
		Constant.CONFIG_URL = "./xml/config.xml";
		Constant.EXCEL_URL = "./excelFile";
		Constant.OUTPUT_URL = "./out";
		Constant.LANG_FILE = "./lang/" + Constant.TARGET;
		Constant.TEMPLATE_FILE = "./template/template_" + Constant.TARGET + ".txt";

		Properties props = engine.loadScript(Constant.LANG_FILE);
		String content = FileUtils.readContent(Constant.TEMPLATE_FILE);

		Document doc = sax.read(Constant.CONFIG_URL);
		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		Iterator<Element> nodeIt = root.elementIterator();
		while (nodeIt.hasNext()) {
			ClassConfig node = new ClassConfig(nodeIt.next());
			System.out.println(node.className);
			if (node.typeList.contains(Constant.TARGET)) {
				// 生成代码
				String result = engine.createCode(node, props, content);
				// 生成源码文件
				File file = FileUtils.createFile(Constant.OUTPUT_URL,
						node.code + props.getProperty(LangScriptExtensionKeyName.PREFIX));
				FileUtils.writeFile(Constant.OUTPUT_URL + FileUtils.fileSplit + file.getName(), result);

				// 生成数据
				Data data = dataCreator.getData(node, Constant.EXCEL_URL);
				// 生成数据文件
				FileUtils.writeData(Constant.OUTPUT_URL, node.out, data.build());
			}
		}
	}

}
