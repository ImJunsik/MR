package com.examples.web;

import com.mr.step.service.MrMailService;
import com.mr.step.service.impl.MrMailServiceImpl;

public class TestLimitDateMrSendMail {
	
	private static MrMailServiceImpl mrMailService;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		mrMailService = new MrMailServiceImpl();
		mrMailService.limitDateMrSendMail();
	}

}
