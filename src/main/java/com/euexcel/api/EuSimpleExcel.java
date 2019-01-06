package com.euexcel.api;

import java.util.List;

import com.euexcel.core.EuExcelService;
import com.euexcel.core.SimpleEuExcelService;

public class EuSimpleExcel<T> implements EuExcel{
	

	private EuExcelService euExcel;
	
	
	public EuSimpleExcel(Class<?> t){
		this(t, new SimpleEuExcelService(t));
	}
	
	public EuSimpleExcel(Class<?> t,EuExcelService euExcel){
		this.euExcel=euExcel;
	}
	
	@SuppressWarnings("hiding")
	public <T> void write(String fileName, List<T> dataList, String sheetName) {
		try {
			euExcel.write(fileName, dataList, sheetName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@SuppressWarnings("hiding")
	public <T> List<T> read(String fileName) {
		return euExcel.read(fileName);
	}

}
