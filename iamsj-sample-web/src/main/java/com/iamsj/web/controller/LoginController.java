package com.iamsj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.code.kaptcha.Producer;

@Controller
public class LoginController {

	@Autowired
	private Producer producer;
	
	
}
