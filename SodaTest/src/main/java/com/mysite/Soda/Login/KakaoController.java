package com.mysite.Soda.Login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class KakaoController {

	private final KakaoService kakaoService;
	private final LoginService loginService;
	
	@GetMapping("/SODA/kakao/callback")
	public String kakaoCallback(@RequestParam(value="code") String code, HttpSession session) throws Throwable {
		String access_Token = kakaoService.getKakaoAccessToken(code);
		System.out.println("controller-access_Token : " + access_Token);
		
		String email = kakaoService.KakaoUser(access_Token);
		System.out.println("controller-userEmail : " + email);
		
		session.setAttribute("access_Token", access_Token);
		session.setAttribute("kakaoEmail", email);
		session.setMaxInactiveInterval(600);
		return "redirect:/SODA/kakao/login";
	}
	
	@GetMapping("/SODA/kakao/login")
	public String KakaoLogin(HttpSession session) {
		String access_Token = (String) session.getAttribute("access_Token");
		String email = (String) session.getAttribute("kakaoEmail");
		Integer memberId = loginService.findMemberId(email);
		
		System.out.println("kakaoController-access_Token : " + access_Token);
		System.out.println("kakaoController-email : " + email);
		System.out.println("kakaoController-memberId : " + memberId);
		
		if(memberId != null) {
			session.setAttribute("access_Token", access_Token);
			session.setAttribute("memberId", memberId);
			return "redirect:/SODA_DASHBOARD";
		}
		else {
			session.setAttribute("email", email);
			session.setMaxInactiveInterval(600);
			return "SODA_JoinSelect";
		}
	}

}
