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

	private Map<String, Method> fieldMethodRelation=new HashMap<String,Method>();

	private List<CellInfoEvt> cellList=new ArrayList<CellInfoEvt>();

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
				throw new Exception("Orderֵ����Ϊ��");
			}
			if(set.contains(cellInfo.getOrder())){
				throw new Exception("�ظ���Orderֵ:"+cellInfo.getOrder());
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
