package com.mysite.Soda.Statistic;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StatisticController {
	
	private final ConnectionService connectionservice;
	private final HttpSession session;
	@GetMapping("/ConnectStatistic")
	public String GetConnStatistic(Model model){
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		List<ConnectionVO> connectlist = connectionservice.getconnectionstatistic(member_id);
		model.addAttribute("connectlist",connectlist);
		return "ConnectStatistic.html";
	}
	@GetMapping("/UsageStatistic")
	public String GetUseStatistic(Model model){
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		List<ConnectionVO> connectlist = connectionservice.getuseagestatistic(member_id);
		model.addAttribute("connectlist",connectlist);
		return "UsageStatistic.html";
	}
	
	
}
