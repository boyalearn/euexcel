package com.euexcel.api;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface EuExcel {

	/**
	 *
	 * @param fileName 全文件路径
	 * @param dataList 需要导入Excel表格数据
	 * @param sheetName sheet表名称
	 */
	<T> void write(String fileName,List<T> dataList,String sheetName);


	/**
	 *
	 * @param fileName 全文件路径
	 * @param dataList 需要导入Excel表格数据
	 * @param sheetName sheet表名称
	 */
	<T> void write(String fileName, List<T> dataList, String sheetName, HttpServletResponse response);


	/**
	 *
	 * @param fileName 读取文件路径
	 * @return  返回数据List
	 */
	<T> List<T> read(String fileName);
}
