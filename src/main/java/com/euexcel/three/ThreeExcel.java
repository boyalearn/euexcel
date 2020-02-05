package com.euexcel.three;

import java.util.List;

import com.euexcel.core.EuMateInfo;
import com.euexcel.exception.WorkbookException;

public interface ThreeExcel {

	public <T> void writeData(List<T> dataList, String fileName, String sheetName, EuMateInfo euMateInfo)
			throws WorkbookException, Exception;

	public List<Object> readData(String fileName, EuMateInfo euMateInfo) throws WorkbookException, Exception;
}
