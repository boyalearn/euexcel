package com.euexcel.three;

import java.util.List;

import com.euexcel.core.EuMateInfo;

public interface ThreeExcel {
	public <T> void writeData(List<T> dataList,String fileName,String sheetName,EuMateInfo euMateInfo);
	
	public List<Object> readData(String fileName,EuMateInfo euMateInfo);
}
