package com.kdt.oop;

import com.kdt.oop.util.HmsType;

public class TeacherVO extends PersonVO{

    private String subject;

	public TeacherVO() {
		
	}

	public TeacherVO(HmsType type, String name, int age, String address, String subject) {
		this.setType(type);
		this.setName(name);
        this.setAge(age);
        this.setAddress(address);
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String teaInfo() {
		return super.perInfo() + ", subject = " + subject;
	}
	
	public String perInfo() {
		return super.perInfo() + ", subject = " + subject;
	}
	
	@Override
	public String Info() {
		return  "tea-" + super.Info()+ "-" +subject;
	}
    
    
}
