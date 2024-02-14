package com.mysite.Soda.Login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SODA/naver")
public class NaverController {
	
	@GetMapping("/callback")
	public String callbackNaver(@RequestParam(value="code") String code) {
		return "code : " + code;
	}
}
