package com.mysite.Soda.Login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/SODA")
public class ChangePwController {
	
	private final ChangePwService changePwService;
	
	@GetMapping("/ChangePwForm")
	public String ChangePw() {
		return "SODA_ChangePw";
	}
	
	@PostMapping("/ChangePw")
	public String ChangePw(@RequestParam(name="userEmail") String email, @RequestParam(name="inputChangePw") String pw, Model model) {
		boolean success = changePwService.changePw(email, pw);
		
		if(success) {
			return "SODA_ChangePWSuccess";
		} else {
			return "SODA_ChangePw?error=wrongPw";
		}
	}
	
}
