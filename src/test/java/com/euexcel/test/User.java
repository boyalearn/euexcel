package com.euexcel.test;

import com.euexcel.annotation.Cell;

public class User {
	
	@Cell(name="name",order=1,cellWidth=400)
	private String name;
	
	@Cell(name="age",order=0,cellWidth=400)
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
	
	
}
