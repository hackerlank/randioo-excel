package com.randioo.config.randioo_excel;

import java.util.HashMap;
import java.util.Map;

import com.randioo.config.randioo_excel.service.Service;
import com.randioo.config.randioo_excel.service.ServiceImpl;

/**
 * Hello world!
 *
 */
public class RandiooConfigExcelApp {
	public static void main(String[] args) throws Exception {

//		if (args.length != 0) {
			String command = "config_url=./xml/config.xml" + " java_template_url=./template/templateJava.txt"
					+ " as_template_url=./template/templateAS.txt" + " excel_url=./excelFile" + " data_url=./out"
					+ " po_var_name=config" + " bytes_var_name=data";
			args = command.split(" ");

			Map<String, String> keyValueMap = new HashMap<>();
			for (int i = 0; i < args.length; i++) {
				String[] keyValue = args[i].split("=");
				keyValueMap.put(keyValue[0], keyValue[1]);
			}

			if (!check(keyValueMap))
				return;

			Constant.CONFIG_URL = keyValueMap.get("config_url");
			Constant.TEMPLATE_JAVA_URL = keyValueMap.get("java_template_url");
			Constant.TEMPLATE_AS_URL = keyValueMap.get("as_template_url");
			Constant.EXCEL_URL = keyValueMap.get("excel_url");
			Constant.OUTPUT_URL = keyValueMap.get("data_url");
			Constant.PO = keyValueMap.get("po_var_name");
			Constant.BYTES_VAR_NAME = keyValueMap.get("bytes_var_name");

			Service service = new ServiceImpl();
			service.createCode();
			Command.exeCmd(Constant.OUTPUT_URL, Constant.OUTPUT_URL, "tbl");
//		} else {
//			showUI();
//		}

	}
	
	public static void showUI(){
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(RandiooExcelFrame.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(RandiooExcelFrame.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(RandiooExcelFrame.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(RandiooExcelFrame.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new RandiooExcelFrame().setVisible(true);
			}
		});
	}

	public static boolean check(Map<String, String> map) {
		boolean flag = true;
		flag = check2(map, "config_url", flag);
		flag = check2(map, "java_template_url", flag);
		flag = check2(map, "as_template_url", flag);
		flag = check2(map, "excel_url", flag);
		flag = check2(map, "data_url", flag);
		flag = check2(map, "po_var_name", flag);
		flag = check2(map, "bytes_var_name", flag);

		return flag;
	}

	private static boolean check2(Map<String, String> map, String key, boolean flag) {
		if (map.get(key) == null) {
			System.out.println("need " + key);
			return false;
		}
		if (!flag)
			return false;

		return true;

	}

	

}
