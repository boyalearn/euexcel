package com.euexcel.evt;

public class FieldToTypeEvt {
	
	private String fieldName;
	
	private Object filedType;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getFiledType() {
		return filedType;
	}

	public void setFiledType(Object filedType) {
		this.filedType = filedType;
	}
}
