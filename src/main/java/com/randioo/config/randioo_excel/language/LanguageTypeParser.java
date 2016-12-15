package com.randioo.config.randioo_excel.language;

/**
 * 配置表提供的基本数据类型转换器
 * 
 * @author AIM
 *
 */
public interface LanguageTypeParser {
	/**
	 * 字符串
	 * 
	 * @return
	 */
	public String getString();

	/**
	 * 整形（4个字节）
	 * 
	 * @return
	 */
	public String getInt();

	/**
	 * 布尔值
	 * 
	 * @return
	 */
	public String getBoolean();

	/**
	 * 短整型（2个字节）
	 * 
	 * @return
	 */
	public String getShort();

	/**
	 * 字节
	 * 
	 * @return
	 */
	public String getByte();

	/**
	 * 双精度浮点数（8个字节）
	 * 
	 * @return
	 */
	public String getDouble();

}
