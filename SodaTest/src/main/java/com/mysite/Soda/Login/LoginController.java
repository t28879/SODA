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
public class LoginController {

	private final LoginService loginService;
	
	@GetMapping("/loginPage")
	public String loginPage() {
		return "SODA_Login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam(name="email") String email, @RequestParam(name="pw") String pw, HttpSession session) {
		Integer memberId = loginService.getMemberId(email, pw);
		if(memberId != null) {
			session.setAttribute("memberId", memberId);
			System.out.println("loginCon: " + memberId);
			return "redirect:/SODA_DASHBOARD";
		} else {
			return "redirect:/SODA/loginPage?error=user_not_found";
		}
	}
	
}
