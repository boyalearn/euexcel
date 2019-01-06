package com.euexcel.core;

import java.util.List;

public interface EuExcelService {
	
    public <T> void write(String fileName, List<T> dataList, String sheetName);
	
	public <T> List<T> read(String fileName);

}
