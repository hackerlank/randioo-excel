package com.randioo.config.randioo_excel;

import java.awt.EventQueue;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class MyFocusListener implements FocusListener {

	private Map<JTextField, Integer> map;
	private JFileChooser chooser;
	private JTextField textField;

	public MyFocusListener(Map<JTextField, Integer> typeMap, JFileChooser chooser) {
		map = typeMap;
		this.chooser = chooser;
	}

	public JTextField getTextField() {
		return textField;
	}

	@Override
	public void focusGained(final FocusEvent e) {
		// TODO Auto-generated method stub
		textField = (JTextField) e.getSource();
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(textField==null)return;
				Integer mode = map.get(textField);
				chooser.setFileSelectionMode(mode);
			}
		});
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
	}

}
