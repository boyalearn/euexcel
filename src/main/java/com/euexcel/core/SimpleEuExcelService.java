package com.euexcel.core;

import java.util.List;
import com.euexcel.three.POIExcel;
import com.euexcel.three.ThreeExcel;

public class SimpleEuExcelService implements EuExcelService{
	
	private EuMateInfo euMateInfo;
	
	
	private ThreeExcel threeExcel;
	
	
	public SimpleEuExcelService(Class<?> classType){
		this(classType, new POIExcel());
	}
	
	public SimpleEuExcelService(Class<?> classType,ThreeExcel threeExcel){
		euMateInfo=new EuMateInfo();
		try {
			euMateInfo.initMetaInfo(classType);
		} catch (Exception e) {
			throw new RuntimeException("cell mate exception...");
		}
		this.threeExcel=threeExcel;
	}

	public <T> void write(String fileName, List<T> dataList, String sheetName) {
		threeExcel.writeData( dataList, fileName, sheetName,euMateInfo);
	}

	public List<Object> read(String fileName) {
		return threeExcel.readData(fileName,euMateInfo);
	}
	


}
