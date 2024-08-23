package test.service;

import test.domain.RequestTestDTO;
import test.domain.ResponseTestDTO;
import test.dao.TestDao;

public class TestService {
	
	/////////// spring framework - 의존관계성 주입(Dependency Injectio)
	private TestDao dao;
	public TestService() {
		dao = new TestDao();
	}
	
	public ResponseTestDTO login(RequestTestDTO params) {
		System.out.println("debug >>> service login params = " + params);
		return dao.loginRow(params);
	}
}
