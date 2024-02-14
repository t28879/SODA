package com.mysite.Soda.AdminChangeLog;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminChangeLogController {
	
	private final AdminChangeLogDAO adminchangelogdao;
	private final HttpSession session;
	
	@GetMapping("/AdminChangeLog")
	public String AdminChangeLog(Model model) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		List<AdminChangeLogVO> log =  adminchangelogdao.getdownloadlog(member_id);
		model.addAttribute("log",log);
		return "AdminChangeLog.html";
	}
	
}
