package com.kdt.oop;

import com.kdt.oop.util.HmsType;

public class StudentVO extends PersonVO {

    private String stuId;

	public StudentVO() {
		
	}
	
    public StudentVO(HmsType type, String name, int age, String address, String stuId) {
        this.setType(type);
        this.setName(name);
        this.setAge(age);
        this.setAddress(address);
        this.stuId = stuId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

	public String stuInfo() {
		return super.perInfo() + ", stuId = " + stuId;
	}
	
	// 상속관계에서 부모의 메서드이름과 동일한 이름의 메서드를 
	// 자식이 정의할 수 있다.
	// 오버라이딩(overriding) : 접근지정자, 리턴타입, 메서드이름(매개변수의 타입과 개수) 동일
	// 구현부가 다른걸 오버라이딩이라고 한다.
	@Override
	public String perInfo() {
		return super.perInfo() + ", stuId = " + stuId;
	}
	
	@Override
	public String Info() {
		return  "stu-"+super.Info()+ "-" +stuId ;
	}
    
}






















