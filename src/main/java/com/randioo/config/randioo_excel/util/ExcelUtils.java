package com.randioo.config.randioo_excel.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	/**
	 * 获得一张sheet
	 * 
	 * @param f 文件
	 * @param page sheet 的名字
	 * @return
	 * @author wcy 2016年12月16日
	 */
	public static Sheet sheet(File f, String page) {
		FileInputStream fis = null;
		Workbook workbook = null;
		try {
			fis = new FileInputStream(f);
			workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet(page);
			
			return sheet;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fis !=null){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}							
			}
			if(workbook!=null){
				try {
					workbook.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}
		return null;
	}

	/**
	 * 获得一张sheet
	 * 
	 * @param fileName 文件名
	 * @param path sheet 的名字
	 * @return
	 * @author wcy 2016年12月16日
	 */
	public static Sheet sheet(String fileName, String page) {
		return sheet(new File(fileName), page);
	}
}
