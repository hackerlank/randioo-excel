package com.randioo.config.randioo_excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.randioo.config.randioo_excel.exceptioin.ScriptNotCompleteException;
import com.randioo.config.randioo_excel.po.ClassConfig;
import com.randioo.config.randioo_excel.po.FieldConfig;
import com.randioo.config.randioo_excel.util.ReflectUtils;

public class LangScriptEngine {

	/**
	 * 获得脚本属性
	 * 
	 * @param fileName
	 * @return
	 * @throws ScriptNotCompleteException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Properties loadScript(String fileName)
			throws ScriptNotCompleteException, FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(fileName));
		// 查看需要的配置表属性是否都有
		Field[] basicTypeFields = ReflectUtils.getFields(BasicType.class);
		Field[] extensionFields = ReflectUtils.getFields(LangScriptExtensionKeyName.class);
		Field[] prefixFields = ReflectUtils.getFields(LangScriptPrefixKeyName.class);

		// 检查基本数据类型,和声明方法，赋值方法
		for (Field basicTypeField : basicTypeFields) {
			String basicTypeKey = ReflectUtils.getStaticFieldValue(basicTypeField);
			if (!checkAttributeExist(props, basicTypeKey)) {
				throw new ScriptNotCompleteException("need keyName:" + basicTypeKey + " at " + fileName);
			}
			for (Field prefixField : prefixFields) {
				String prefixKey = ReflectUtils.getStaticFieldValue(prefixField);
				String key = prefixKey + basicTypeKey;
				if (!checkAttributeExist(props, key)) {
					throw new ScriptNotCompleteException("need keyName:" + key + " at " + fileName);
				}
			}
		}

		// 其他键值对检查
		for (Field extensionField : extensionFields) {
			String extensionKey = ReflectUtils.getStaticFieldValue(extensionField);
			if (!checkAttributeExist(props, extensionKey)) {
				throw new ScriptNotCompleteException("need keyName:" + extensionKey + " at " + fileName);
			}
		}
		return props;
	}

	/**
	 * 检查配置表属性是否存在
	 * 
	 * @param props
	 * @param key
	 * @return
	 */
	private boolean checkAttributeExist(Properties props, String key) {
		if (props.containsKey(key)) {
			String value = props.getProperty(key);
			if (value != null && !value.equals("")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 生成成员变量
	 * 
	 * @param config
	 * @param props
	 * @return
	 */
	private String makeFieldsCode(ClassConfig config, Properties props) {
		StringBuilder sb = new StringBuilder();
		for (FieldConfig fieldConfig : config.itemList) {
			// 注释
			// 如果没有填注释，则使用name
			String comment = fieldConfig.comment != null && !fieldConfig.comment.equals("") ? fieldConfig.comment
					: fieldConfig.name;
			String comment1 = props.getProperty(LangScriptExtensionKeyName.COMMENT).replace(Macro.$COMMENT, comment);
			// 声明语句
			// 根据基本数据类型找到声明语句
			String statement = props.getProperty(LangScriptPrefixKeyName.DECLARE_ + fieldConfig.type);
			String type = props.getProperty(fieldConfig.type);
			// 替换类型和变量名
			String statement1 = statement.replace(Macro.$BASE_TYPE, type).replace(Macro.$CODE, fieldConfig.code);
			// 添加注释和语句
			sb.append(comment1).append(statement1);
		}
		return sb.toString();
	}

	/**
	 * 生成赋值语句
	 * 
	 * @param config
	 * @param props
	 * @return
	 */
	private String makeAssignCode(ClassConfig config, Properties props) {
		StringBuilder sb = new StringBuilder();
		for (FieldConfig fieldConfig : config.itemList) {
			// 赋值语句
			// 根据基本数据类型找到声明语句
			String statement = props.getProperty(LangScriptPrefixKeyName.ASSIGNMENT_ + fieldConfig.type);
			String type = props.getProperty(fieldConfig.type);
			// 替换类型和变量名
			String statement1 = statement.replace(Macro.$BASE_TYPE, type).replace(Macro.$CODE, fieldConfig.code);
			sb.append(statement1);
		}
		return sb.toString();
	}

	/**
	 * 生成代码
	 * 
	 * @param config
	 *            xml节点
	 * @param props
	 *            语言特性表
	 * @param template
	 *            语言模版
	 * @return
	 */
	public String createCode(ClassConfig config, Properties props, String template) {
		String declare = makeFieldsCode(config, props);
		String assign = makeAssignCode(config, props);

		template = template.replace(Macro.$DECLARE, declare).replace(Macro.$ASSIGN, assign).replace(Macro.$URL,
				config.out);

		String KEY_TYPE = null;
		String KEY_CODE = null;
		// 如果没有key则使用列表方式存储,有key使用映射表方式存储
		if (config.key == null || config.key.equals("")) {
			template = template
					.replace(Macro.$MAKE_DIC_TYPE, props.getProperty(LangScriptExtensionKeyName.LIST_DECLARE_CLASS))
					.replace(Macro.$DIC_TYPE, props.getProperty(LangScriptExtensionKeyName.LIST_CLASS))
					.replace(Macro.$DIC_ADD, props.getProperty(LangScriptExtensionKeyName.LIST_ADD_METHOD))
					.replace(Macro.$DIC_VAR_NAME, props.getProperty(LangScriptExtensionKeyName.LIST_VAR_NAME));
		} else {
			Map<String,String> keyTypeMap = new HashMap<>();
			keyTypeMap.put(Macro.$BASE_CLASS_TYPE, LangScriptPrefixKeyName.CLASS_);
			keyTypeMap.put(Macro.$BASE_TYPE, "");
			// 获得KeyType
			for (FieldConfig fieldConfig : config.itemList) {
				if (config.key.equals(fieldConfig.name)) {
					// 确认数据结构中的基本类型使用类型
					String keyTypeUse = props.getProperty(LangScriptExtensionKeyName.KEY_TYPE_USE);
					KEY_TYPE = props.getProperty(keyTypeMap.get(keyTypeUse) + fieldConfig.type);
					KEY_CODE = fieldConfig.code;
					break;
				}
			}
			template = template
					.replace(Macro.$MAKE_DIC_TYPE, props.getProperty(LangScriptExtensionKeyName.MAP_DECLARE_CLASS))
					.replace(Macro.$DIC_TYPE, props.getProperty(LangScriptExtensionKeyName.MAP_CLASS))
					.replace(Macro.$DIC_ADD, props.getProperty(LangScriptExtensionKeyName.MAP_ADD_METHOD))
					.replace(Macro.$DIC_VAR_NAME, props.getProperty(LangScriptExtensionKeyName.MAP_VAR_NAME));
		}
		// 如果有填主键属性
		if (KEY_TYPE != null) {
			template = template.replace(Macro.$KEY_TYPE, KEY_TYPE).replace(Macro.$KEY_CODE, KEY_CODE);
		}
		template = template.replace(Macro.$BUFFER, "buffer").replace(Macro.$CONFIG, "config")
				.replace(Macro.$DECLARE_BRACE, props.getProperty(LangScriptExtensionKeyName.DECLARE_BRACE))
				.replace(Macro.$ASSIGNMENT_BRACE, props.getProperty(LangScriptExtensionKeyName.ASSIGNMENT_BRACE))
				.replace(Macro.$CLASS_NAME, config.className);

		return template;
	}

}
