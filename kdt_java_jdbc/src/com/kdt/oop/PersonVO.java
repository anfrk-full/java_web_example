package com.kdt.oop;

import java.io.Serializable;

import com.kdt.oop.util.HmsType;

public class PersonVO implements Serializable {

	private HmsType type;
	private String name;
    private int age;
    private String address;

    public PersonVO(HmsType type, String name, int age, String address) {
		this.type = type;
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public PersonVO() {

	}
	
	public HmsType getType() {
		return type;
	}

	public void setType(HmsType type) {
		this.type = type;
	}

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
        if(age < 0) {
            System.out.println("ㅗ");
        } 
        else {
        this.age = age;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	
	public String perInfo() {
		return "name=" + name + ", age=" + age + ", address=" + address;
	}
	
	public String Info() {
		return  name + "-" + age + "-" + address ;
	}
    
	
    
}






















