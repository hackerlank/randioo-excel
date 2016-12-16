package com.randioo.config.randioo_excel.cache;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.randioo.config.randioo_excel.BasicType;
import com.randioo.config.randioo_excel.util.ReflectUtils;
import com.randioo.config.randioo_excel.util.StringUtils;

public class StringCache {
	/**
	 * 字符串信息初始化
	 * 
	 * @author wcy 2016年12月16日
	 */
	public static void StringCacheInit() {
		Field[] fields = ReflectUtils.getFields(BasicType.class);
		for (Field field : fields) {
			// 基础类型与获得方法的名称初始化
			String basicType = ReflectUtils.getFieldValue(null, field);
			String methodName = "get" + StringUtils.firstStrToUpperCase(basicType);
			basicTypeMethodNameMap.put(basicType, methodName);
		}
	}

	// 查找语言类型的方法映射表（配置表中的基本类型，get+配置表中的基本类型）
	private static Map<String, String> basicTypeMethodNameMap = new HashMap<>();

	/**
	 * 获得语言基础类型的方法
	 * 
	 * @param name
	 * @return
	 * @author wcy 2016年12月16日
	 */
	public static String getLanguageBasicTypeMethodName(String basicType) {
		return basicTypeMethodNameMap.get(basicType);
	}

}
