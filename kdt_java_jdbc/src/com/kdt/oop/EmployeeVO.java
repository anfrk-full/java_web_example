package com.kdt.oop;

import com.kdt.oop.util.HmsType;

public class EmployeeVO extends PersonVO{

    private String dept;

	public EmployeeVO() {
		
	}

	public EmployeeVO(HmsType type,String name, int age, String address, String dept) {
		this.setType(type);
		this.setName(name);
        this.setAge(age);
        this.setAddress(address);
		this.dept = dept;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
    
	public String empInfo() {
		return super.perInfo() + ", dept=" + dept;
	}
	
	@Override
	public String perInfo() {
		return super.perInfo() + ", dept=" + dept;
	}
	
	@Override
	public String Info() {
		return  "emp-" + super.Info() + "-" + dept;
	}
    
}
