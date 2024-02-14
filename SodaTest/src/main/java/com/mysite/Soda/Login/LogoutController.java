package com.mysite.Soda.Login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LogoutController {

	private final KakaoService kakaoService;
	
	@PostMapping("/SODA/Logout")
	@ResponseBody
	public String Logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "로그아웃 되었습니다.";
    }
	
	@PostMapping("/SODA/logout")
	@ResponseBody
	public String logout(HttpSession session) {
		String access_Token = (String) session.getAttribute("access_Token");
		if(access_Token != null) {
			kakaoService.unlink(access_Token);
			//kakaoService.kakaoLogout(access_Token);
			session.removeAttribute("access_Token");
		}
		session.invalidate();
		return "로그아웃 되었습니다.";
	}
}
