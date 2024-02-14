package com.mysite.Soda.Login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequestMapping("/SODA")
@Controller
@RequiredArgsConstructor
public class FindEmailController {
	
	private final FindEmailService findEmailService;
	
	@GetMapping("/findEmailForm")
	public String FindEmailForm() {
		return "SODA_FindEmail";
	}
	
	@PostMapping("/FindEmail")
	public String FindEmail(@RequestParam(name="inputPw") String email, HttpSession session) {
		String findEmail = findEmailService.getEmail(email);
		if(findEmail != null) {
			session.setAttribute("findEmail", findEmail);
			return "SODA_ChangePw";
		} else {
			return "redirect:/SODA/findEmailForm?error=email_not_found";
		}
	}
	
}
