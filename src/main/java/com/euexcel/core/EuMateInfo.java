package com.euexcel.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.euexcel.evt.CellInfoEvt;
import com.euexcel.util.ReflectUtil;

public class EuMateInfo {
	
	private Class<?> classType;
	
	//保存file与method的关心
	private Map<String, Method> fieldMethodRelation=new HashMap<String,Method>();
	
	//保存Excel表单第一列标题
	private List<CellInfoEvt> cellList=new ArrayList<CellInfoEvt>();
	
	//初始化Mate信息
	public void initMetaInfo(Class<?> classType) throws Exception{
		this.classType=classType;
		ReflectUtil.getMethodMap(classType, fieldMethodRelation);
		getTableHeadInfo(classType);
		checkCellInfo(this.cellList);
	}
	
	private void getTableHeadInfo(Class<?> classType){
		this.cellList=ReflectUtil.getTableHeadInfo(classType);
	}
	
	private void checkCellInfo(List<CellInfoEvt> cellList) throws Exception{
		Set<Integer> set=new TreeSet<Integer>();
		for(CellInfoEvt cellInfo:cellList){
			if(cellInfo.getOrder()<0){
				throw new Exception("Order值不能为负");
			}
			if(set.contains(cellInfo.getOrder())){
				throw new Exception("重复的Order值:"+cellInfo.getOrder());
			}else{
				set.add(cellInfo.getOrder());
			}
		}
	}

	public Class<?> getClassType() {
		return classType;
	}

	public void setClassType(Class<?> classType) {
		this.classType = classType;
	}

	public Map<String, Method> getFieldMethodRelation() {
		return fieldMethodRelation;
	}

	public void setFieldMethodRelation(Map<String, Method> fieldMethodRelation) {
		this.fieldMethodRelation = fieldMethodRelation;
	}

	public List<CellInfoEvt> getCellList() {
		return cellList;
	}

	public void setCellList(List<CellInfoEvt> cellList) {
		this.cellList = cellList;
	}
	
}
