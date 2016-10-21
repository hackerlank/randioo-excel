package com.randioo.config.randioo_excel;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Data {
	private ByteOrder order = ByteOrder.LITTLE_ENDIAN;
	private Charset charset = Charset.forName("utf-8");

	private ByteBuffer dataBuffer;
	private List<Byte> dataList = new ArrayList<>();

	public static Data wrap(byte[] data) {
		Data value = new Data(data);
		return value;
	}

	public static Data create() {
		return new Data();
	}

	public Data(byte[] data) {
		dataBuffer = ByteBuffer.wrap(data);
		dataBuffer.order(order);
	}

	public Data() {
		// TODO Auto-generated constructor stub
	}

	public ByteOrder getOrder() {
		return order;
	}

	public void setOrder(ByteOrder order) {
		this.order = order;
	}

	public Data putInt(int value) {
		ByteBuffer buf = ByteBuffer.allocate(Integer.SIZE / 8);
		buf.order(order);
		byte[] bytes = buf.putInt(value).array();

		for (Byte b : bytes) {
			dataList.add(b);
		}

		return this;
	}

	public int getInt() {
		return dataBuffer.getInt();
	}

	public Data putShort(short value) {
		ByteBuffer buf = ByteBuffer.allocate(Short.SIZE / 8);
		buf.order(order);
		buf.putShort(value);
		byte[] array = buf.array();
		for (Byte b : array) {
			dataList.add(b);
		}
		return this;
	}

	public short getShort() {
		return dataBuffer.getShort();
	}

	public Data putByte(byte value) {
		dataList.add(value);
		return this;
	}

	public byte getByte() {
		return dataBuffer.get();

	}

	public Data putString(String value) {
		byte[] bytes = value.getBytes(charset);
		int len = bytes.length;
		this.putShort((short) len);
		for (Byte b : bytes) {
			dataList.add(b);
		}
		return this;
	}

	public String getString() {
		short len = this.getShort();
		byte[] bytes = new byte[len];
		for (int i = 0; i < len; i++) {
			bytes[i] = dataBuffer.get();
		}
		String str = new String(bytes, charset);
		return str;
	}

	public Data putBoolean(boolean value) {
		byte result = (byte) (value ? 1 : 0);
		dataList.add(result);
		return this;
	}

	public boolean getBoolean() {
		byte value = dataBuffer.get();
		return value == 1 ? true : false;
	}

	public Data putDouble(double value) {
		ByteBuffer buf = ByteBuffer.allocate(Double.SIZE / 8);
		buf.order(order);
		byte[] data = buf.putDouble(value).array();
		for (Byte b : data) {
			dataList.add(b);
		}
		return this;
	}

	public double getDouble() {
		return dataBuffer.getDouble();
	}

	public List<Byte> getDataList() {
		return dataList;
	}

	public boolean hasRemaining() {
		return dataBuffer.hasRemaining();
	}

	public byte[] build() {
		int size = dataList.size();
		byte[] b = new byte[size];
		for (int i = 0; i < size; i++) {
			b[i] = dataList.get(i);
		}
		return b;
	}

}
