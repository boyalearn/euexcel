package com.euexcel.api;

import java.util.List;

public interface EuExcel {
	
	/**
	 * 
	 * @param fileName ȫ�ļ�·��
	 * @param dataList ��Ҫ����Excel�������
	 * @param sheetName sheet������
	 */
	public <T> void write(String fileName,List<T> dataList,String sheetName);
	
	
	/**
	 * 
	 * @param fileName ��ȡ�ļ�·��
	 * @return  ��������List
	 */
	public <T> List<T> read(String fileName);
}
