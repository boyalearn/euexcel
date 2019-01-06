package com.euexcel.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.euexcel.annotation.Cell;
import com.euexcel.evt.CellInfoEvt;

public class ReflectUtil {
	
	private static final String GET="get";
	
	private static final String SET="set";
	
	public static void getMethodMap(Class<?> classType,Map<String, Method> methodMap){
		Field[] fields=classType.getDeclaredFields();
		for(Field field:fields){
			Method[] methods=classType.getDeclaredMethods();
			for(Method method: methods){
				String getMethod=GET+StringUtil.upFist(field.getName());
				String setMethod=SET+StringUtil.upFist(field.getName());
				if(method.getName().equals(getMethod)){
					methodMap.put(getMethod, method);
				}else if(method.getName().equals(setMethod)){
					methodMap.put(setMethod, method);
				}
			}
		}
	} 
	
	public static Method getSetMethod(Map<String,Method> map,String filedName){
		String getMethod=SET+StringUtil.upFist(filedName);
		return map.get(getMethod);
	}
    public static Method getGetMethod(Map<String,Method> map,String filedName){
    	String setMethod=GET+StringUtil.upFist(filedName);
    	return map.get(setMethod);
	}
    public static List<CellInfoEvt> getTableHeadInfo(Class<?> classType){
    	List<CellInfoEvt> list=new ArrayList<CellInfoEvt>();
    	Field[] fields=classType.getDeclaredFields();
    	for(Field field:fields){
    		Annotation[] anns=field.getAnnotations();
    		for(Annotation ann:anns){
    			if(ann.annotationType().isAssignableFrom(Cell.class)){
    				CellInfoEvt cellInfo=new CellInfoEvt();
    				Cell annCell=(Cell)ann;
    				cellInfo.setName(annCell.name());
    				cellInfo.setOrder(annCell.order());
    				cellInfo.setCellWidth(annCell.cellWidth());
    				cellInfo.setFieldName(field.getName());
    				list.add(cellInfo);
    			}
    		}
    	}
		return list;
	}
	
	
	public static Object exe(Object bean, Method method,Object ... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return method.invoke(bean,args);
	}
}
