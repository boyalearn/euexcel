package com.euexcel.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.euexcel.api.EuSimpleExcel;

public class EuExcelTest {
	
	@Test
	public void testOne(){
		List<User> list=new ArrayList<User>();
		User user=new User();
		user.setName("erwer");
		user.setAge(18);
		list.add(user);
		new EuSimpleExcel<User>(User.class).write("F:\\demo.xls", list, "demosheet");
	}
	
	@Test
	public void testTwo(){
		List<User> list=new ArrayList<User>();
		list=new EuSimpleExcel<User>(User.class).read("F:\\demo2.xlsx");
		System.out.println(list);
	}
	
	public static void main(String args[]){
		
		List<User> list=new ArrayList<User>();
		User user=new User();
		user.setName("erwer");
		user.setAge(18);
		list.add(user);
		new EuSimpleExcel<User>(User.class).write("F:\\demo2.xlsx", list, "demosheet");
		
		/*List<User> list=new ArrayList<User>();
		//list=new EuSimpleExcel<User>(User.class).read("F:\\demo2.xlsx");
		list=new EuSimpleExcel<User>(User.class).read("F:\\demo.xls");
		System.out.println(list);*/
	}
}
