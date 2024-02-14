package com.mysite.Soda.FileDownLoadLog;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FileDownLoadLogController {
	
	private final FileDownLoadlogDAO filedownloadlogdao;
	private final HttpSession session;
	@GetMapping("/DownLoadLog")
	public String GetConnStatistic(Model model) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		List<FileDownLoadLogVO> log =  filedownloadlogdao.getdownloadlog(member_id);
		model.addAttribute("log",log);
		return "DownLoadLog.html";
	}
	
}
