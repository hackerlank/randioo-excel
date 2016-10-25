package com.randioo.config.randioo_excel;

import java.io.IOException;

public class Command {
	public static void exeCmd(String inputPath, String outputPath, String prefix) {
		try {
			// Runtime.getRuntime().exec("cmd.exe /k start zip.bat ./out E: tbl");
			Runtime.getRuntime().exec("cmd.exe /c zip.bat " + inputPath + " " + outputPath + " " + prefix);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
