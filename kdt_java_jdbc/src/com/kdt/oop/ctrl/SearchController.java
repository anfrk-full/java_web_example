package com.kdt.oop.ctrl;

import com.kdt.oop.PersonVO;
import com.kdt.oop.logic.HmsBackService;

public class SearchController {

	private HmsBackService service;
	
	public SearchController() {
		service = new HmsBackService();
	}
	
	public PersonVO execute(String name) {
		return service.searchProcess(name);
	}
}
