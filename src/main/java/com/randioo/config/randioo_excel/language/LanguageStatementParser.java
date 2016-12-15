package com.randioo.config.randioo_excel.language;

/**
 * 语句转换器
 * @author AIM
 *
 */
public interface LanguageStatementParser {
	/**
	 * 根据类型进行声明语句格式化
	 * @param valueType
	 * @return
	 */
	public String declareStatementFormatter(String valueType);

	public String assignmentFormatter(String valueType);

	/**
	 * 注释格式化
	 * @return
	 */
	public String getCommentFormat();

	/**
	 * 声明空格
	 * @return
	 */
	public String getDeclareBrace();

	/**
	 * 获取数据
	 * @return
	 */
	public String getAssignBrace();
	
	/**
	 * 获得数据结构类
	 * @param key
	 * @return
	 */
	public String getDataStructureClass(String key);
	
	/**
	 * 数据结构添加对象的方法
	 * @param key
	 * @return
	 */
	public String getDataStructureAddMethod(String key);
}
