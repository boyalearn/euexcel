package com.euexcel.core;

import java.io.OutputStream;
import java.util.List;

import com.euexcel.exception.WorkbookException;
import com.euexcel.three.HSSPOIExcel;
import com.euexcel.three.XSSPOIExcel;

import javax.servlet.http.HttpServletResponse;

public class SimpleEuExcelService implements EuExcelService{

	private EuMateInfo euMateInfo;


	public SimpleEuExcelService(Class<?> classType){
		euMateInfo=new EuMateInfo();
		try {
			euMateInfo.initMetaInfo(classType);
		} catch (Exception e) {
			throw new RuntimeException("cell mate exception...");
		}
	}

	public <T> void write(String fileName, List<T> dataList, String sheetName) {
		new XSSPOIExcel().writeData( dataList, fileName, sheetName,euMateInfo);
	}

	public <T> void write(String fileName, List<T> dataList, String sheetName, HttpServletResponse response) {
		new XSSPOIExcel().writeData(dataList, fileName, sheetName, euMateInfo, response);
	}

	public List<Object> read(String fileName) {
		WorkbookException currException;
		try {
			return new XSSPOIExcel().readData(fileName,euMateInfo);
		} catch (WorkbookException e) {
			currException=e;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		if(null!=currException){
			try {
				return new HSSPOIExcel().readData(fileName,euMateInfo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}



}
