# euexcel

## 简介
一个易于使用的Excel导入导出工具

## 使用说明

### 第一步 创建实体bean

public class User {
	
	@Cell(name="姓名",order=1,cellWidth=400)
	private String name;
	
	@Cell(name="年龄",order=0,cellWidth=400)
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
	
	
}

### 第二步 使用EuExcel进行Excel的读写
public class EuExcelTest {
	
	@Test
	public void testOne(){
		List<User> list=new ArrayList<User>();
		User user=new User();
		user.setName("erwer");
		user.setAge(18);
		list.add(user);
		new EuSimpleExcel<User>(User.class).write("F:\\demo.xls", list, "用户列表");
	}
	
	@Test
	public void testTwo(){
		List<User> list=new ArrayList<User>();
		list=new EuSimpleExcel<User>(User.class).read("F:\\demo.xls");
		System.out.println(list);
	}
}

## 设计图

![avatar](https://github.com/boyalearn/euexcel/blob/master/src/test/resources/EuExcelDisign.png)
