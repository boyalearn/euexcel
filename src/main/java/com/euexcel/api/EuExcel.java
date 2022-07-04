package com.euexcel.api;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface EuExcel {

	/**
	 *
	 * @param fileName ȫ�ļ�·��
	 * @param dataList ��Ҫ����Excel�������
	 * @param sheetName sheet������
	 */
	<T> void write(String fileName,List<T> dataList,String sheetName);


	/**
	 *
	 * @param fileName ȫ�ļ�·��
	 * @param dataList ��Ҫ����Excel�������
	 * @param sheetName sheet������
	 */
	<T> void write(String fileName, List<T> dataList, String sheetName, HttpServletResponse response);


	/**
	 *
	 * @param fileName ��ȡ�ļ�·��
	 * @return  ��������List
	 */
	<T> List<T> read(String fileName);
}
