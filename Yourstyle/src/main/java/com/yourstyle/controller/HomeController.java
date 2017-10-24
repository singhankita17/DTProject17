package com.yourstyle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value="terms")
	public String showTermsOfUse(){
		return "termsOfUse";
	}
	
	@RequestMapping(value="privacy")
	public String showPrivacyPolicy(){
		return "privacypolicy";
	}
}
