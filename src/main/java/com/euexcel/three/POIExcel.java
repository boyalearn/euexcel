package com.euexcel.three;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.euexcel.core.EuMateInfo;
import com.euexcel.evt.CellInfoEvt;
import com.euexcel.util.ReflectUtil;

public class POIExcel implements ThreeExcel{
	
	/**
	 * key         dataType
	 * value       poiType 
	 */
	private Map<Object,Object> poiTypeMap=new HashMap<Object,Object>();
	
	
	/**
	 *  void setCellValue(double value);
	 *  void setCellValue(Date value);
	 *  void setCellValue(Calendar value);
	 *  void setCellValue(RichTextString value);
	 *  void setCellValue(String value);
	 */
	public POIExcel(){
		poiTypeMap.put(String.class, String.class);
		poiTypeMap.put(Calendar.class, Calendar.class);
		poiTypeMap.put(Date.class, Date.class);
		poiTypeMap.put(Double.class, Double.class);
		poiTypeMap.put(Integer.class, Double.class);
		poiTypeMap.put(int.class, Double.class);
		poiTypeMap.put(long.class, Double.class);
		poiTypeMap.put(Long.class, Double.class);
	}

	public List<Object> readData(String fileName,EuMateInfo euMateInfo){
		try {
			return doReadData(fileName,euMateInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("resource")
	private List<Object> doReadData(String fileName,EuMateInfo euMateInfo) throws Exception {
		FileInputStream in;
		HSSFWorkbook workBook;
		try {
			in=new FileInputStream(fileName);
			workBook=new HSSFWorkbook(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		Sheet sheet=workBook.getSheetAt(0);
		List<CellInfoEvt> celllist=euMateInfo.getCellList();
		List<Object> list=new ArrayList<Object>();
		int rowNum=sheet.getLastRowNum();
		for(int i=1;i<=rowNum;i++){
			Row row=sheet.getRow(i);
			Class<?> clas=euMateInfo.getClassType();
			Object bean=clas.newInstance();
			for(CellInfoEvt cellInfo:celllist){
				Cell cell=row.getCell(cellInfo.getOrder());
				Object data=getCellValue(cell);
				Method method=ReflectUtil.getSetMethod(euMateInfo.getFieldMethodRelation(), cellInfo.getFieldName());
				Class<?> paramType=method.getParameterTypes()[0];
				setMethodInvoke(bean,method,data,paramType);
			}
			list.add(bean);
		}
		return list;
	}
	private void setMethodInvoke(Object bean,Method method,Object data,Class<?> paramType) throws Exception{
		if(String.class.isAssignableFrom(paramType)){
		    ReflectUtil.exe(bean, method, (String)data);
		}else if(int.class.isAssignableFrom(paramType)){
			ReflectUtil.exe(bean, method, Double.valueOf(data.toString()).intValue());
		}else if(long.class.isAssignableFrom(paramType)){
			ReflectUtil.exe(bean, method, Double.valueOf(data.toString()).longValue());
		}else if(Integer.class.isAssignableFrom(paramType)){
			ReflectUtil.exe(bean, method, Double.valueOf(data.toString()).intValue());
		}else if(Long.class.isAssignableFrom(paramType)){
			ReflectUtil.exe(bean, method, Double.valueOf(data.toString()).longValue());
			
		}else if(Date.class.isAssignableFrom(paramType)){
			ReflectUtil.exe(bean, method, (Date)data);
		}
		else if(Calendar.class.isAssignableFrom(paramType)){
			ReflectUtil.exe(bean, method, (Calendar)data);
		}else{
			throw new RuntimeException("不支持的数据异常");
		}
	}
	
	private Object getCellValue(Cell cell){
		Object result = null;
		if(cell!=null){
			switch(cell.getCellType()){
			case Cell.CELL_TYPE_STRING:
				result=cell.getStringCellValue();break;
			case Cell.CELL_TYPE_BLANK:
				result=""; break;
			case Cell.CELL_TYPE_NUMERIC:
				result=cell.getNumericCellValue(); break;
			case Cell.CELL_TYPE_BOOLEAN:
				result=cell.getBooleanCellValue();break;
			default : result=""; break;
			}
			return result;
		}
		return null;
	}
	
	private void SetCellValue(Cell cell,Object data,Class<?> paramType){
		Class<?> poiCls=(Class<?>)poiTypeMap.get(paramType);
		if(null==poiCls){
			cell.setCellValue(data.toString());
		}else if(poiCls.isAssignableFrom(Double.class)){
			cell.setCellValue(Double.valueOf(data.toString()));
		}else if(poiCls.isAssignableFrom(Date.class)){
			cell.setCellValue((Date)data);
		}else if(poiCls.isAssignableFrom(Calendar.class)){
			cell.setCellValue((Calendar)data);
		}else{
			cell.setCellValue(data.toString());
		}
	}

	public <T> void writeData( List<T> dataList, String fileName, String sheetName,EuMateInfo euMateInfo) {
		// TODO Auto-generated method stub
		HSSFWorkbook workBook=new HSSFWorkbook();
		Sheet sheet=workBook.createSheet(sheetName);
		int rowNum=0;
		Row headRow=sheet.createRow(rowNum);
		List<CellInfoEvt> cellList=euMateInfo.getCellList();
		
		for(CellInfoEvt cellInfo:cellList){
			headRow.createCell(cellInfo.getOrder()).setCellValue(cellInfo.getName());;
		}
		rowNum++;
		for(int i=0;i<dataList.size();i++){
			Object bean=dataList.get(i);
			Row dateRow=sheet.createRow(rowNum);
			for(CellInfoEvt cellInfo:cellList){
				Method method=ReflectUtil.getGetMethod(euMateInfo.getFieldMethodRelation(), cellInfo.getFieldName());
				Class<?> returnType=method.getReturnType();
				
				try {
					Cell cell=dateRow.createCell(cellInfo.getOrder());
					SetCellValue(cell,ReflectUtil.exe(bean, method),returnType);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
			rowNum++;
		}
		
		try {
			OutputStream out=new FileOutputStream(fileName);
			workBook.write(out);
			out.close();
			workBook.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
