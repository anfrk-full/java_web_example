package com.kdt.oop.ctrl;

import com.kdt.oop.PersonVO;
import com.kdt.oop.logic.HmsBackService;

public class UpdateController {

	private HmsBackService service;
	
	public UpdateController() {
		service = new HmsBackService();
	}
	
	public void execute(PersonVO person) {
		service.updateProcess(person);
	}
	
	
}
