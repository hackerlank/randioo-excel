package com.randioo.config.randioo_excel.language;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.randioo.config.randioo_excel.BasicType;
import com.randioo.config.randioo_excel.cache.StringCache;
import com.randioo.config.randioo_excel.po.FieldConfig;
import com.randioo.config.randioo_excel.util.ReflectUtils;

public abstract class LanguageParser implements LanguageTypeParser, LanguageStatementParser, LanguagePrefix,
		LanguageFormatterInitInterface {

	// 声明类型的缓存池
	private Map<String, String> declareFormatterMap = new HashMap<>();
	// 声明获取值的缓存池
	private Map<String, String> assignmentFormatterMap = new HashMap<>();
	// 不同语言对应配置表基本数据类型的基本数据类型
	private Map<String, String> basicTypeValueMap = new HashMap<>();

	public LanguageParser() {
		// 格式字符串初始化
		this.initFormatter();
	}

	@Override
	public void initFormatter() {
		Field[] fields = ReflectUtils.getFields(BasicType.class);
		// 循环配置表的基本数据类型
		for (Field field : fields) {
			String basicType = ReflectUtils.getFieldValue(null, field);	

			// 初始化不同语言的基本数据类型
			String methodName = StringCache.getLanguageBasicTypeMethodName(basicType);
			Method method = ReflectUtils.getMethod(getClass(), methodName);
			String result = ReflectUtils.invokeMethodWithResult(this, method);
			basicTypeValueMap.put(basicType, result);

			// 声明语句初始化
			String declareFormat = this.declareStatementFormatter(basicType);
			declareFormatterMap.put(basicType, declareFormat);

			// 赋值语句初始化
			String assignmentFormat = this.assignmentFormatter(basicType);
			assignmentFormatterMap.put(basicType, assignmentFormat);

		}
	}

	/**
	 * 根据配置表中的类型获得对应语言的类型
	 * 
	 * @param basicType
	 * @return
	 */
	public String getLanguageBasicType(String basicType) {
		return basicTypeValueMap.get(basicType);
	}

	/**
	 * 根据类型获得声明语句
	 * 
	 * @param item
	 * @return
	 */
	public String getDeclareFormat(FieldConfig item) {
		return declareFormatterMap.get(item.type);

	}

	/**
	 * 根据某一项属性获得注释
	 * 
	 * @param item
	 * @return
	 */
	public String getComment(FieldConfig item) {
		// 如果没有填注释选项，则默认使用属性名称
		return item.comment != null ? item.comment : item.name;
	}

	/**
	 * 返回每种语言对于类型的获取值的方式
	 * 
	 * @param field
	 * @return
	 */
	public String getAssignmentFormat(FieldConfig field) {
		return assignmentFormatterMap.get(field.type);

	}
}
