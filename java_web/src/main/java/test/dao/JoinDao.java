package test.dao;

import test.domain.RequestJoinDTO;

public class JoinDao {

	public JoinDao() {
		
	}
	
	public String joinRow(RequestJoinDTO params) {
		String msg;
		System.out.println("debug >>> dao joinRow params = " + params);
		msg = "회원가입 완료되었습니다.";
		return msg;
	}
}
