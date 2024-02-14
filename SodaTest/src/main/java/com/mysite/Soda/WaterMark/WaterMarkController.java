package com.mysite.Soda.WaterMark;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WaterMarkController {
	
	private final WaterMarkDAO watermarkdao;
	private final HttpSession session;
	@GetMapping("/WaterMark")
	public String GetConnStatistic(Model model) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		WaterMarkVO wm =  watermarkdao.getwatermark(member_id);
		model.addAttribute("wm",wm.getWM());
		return "WaterMark.html";
	}
	
}
