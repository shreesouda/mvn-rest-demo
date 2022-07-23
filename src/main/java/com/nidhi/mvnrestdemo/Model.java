package com.nidhi.mvnrestdemo;

import javax.xml.bind.annotation.XmlRootElement;

//POJO, i.e Plain Old Java Object.
@XmlRootElement
public class Model {
	
	private String name;
	private int id;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	@Override
	public String toString() {
		return "Model [name=" + name + ", id=" + id + ", age=" + age + "]";
	}
	public void setAge(int age) {
		// TODO Auto-generated method stub
		this.age = age;
		
	}
	
	

}
