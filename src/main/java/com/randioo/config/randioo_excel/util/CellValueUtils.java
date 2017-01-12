package com.randioo.config.randioo_excel.util;

import org.apache.poi.ss.usermodel.Cell;

public class CellValueUtils {
	public static String getCellStringValue(Cell cell) {
		int cellType = cell.getCellType();
		String result = "";
		if (cellType == Cell.CELL_TYPE_NUMERIC) {
			int value = (int) (cell.getNumericCellValue());
			result = value + "";
		} else if (cellType == Cell.CELL_TYPE_STRING) {
			result = cell.getStringCellValue();
		}
		return result;
	}
	
	public static double getCellDoubleValue(Cell cell){
		int cellType = cell.getCellType();
		double value = 0.0;
		if(cellType == Cell.CELL_TYPE_STRING){
			value = Double.parseDouble(cell.getStringCellValue());
		}else if(cellType == Cell.CELL_TYPE_NUMERIC){
			value = cell.getNumericCellValue();
		}
		
		return value;
	}
}
