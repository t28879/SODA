package com.mysite.Soda;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/AdminPage")
    public String root() {
        return "redirect:/CompanyInfo";
    }
	@GetMapping("/InviteMember")
	public String InviteMember() {
		return "InviteMember.html";
	}
	@GetMapping("/OrganizationChart")
	public String OrganizationChart() {
		return "OrganizationChart.html";
	}
	@GetMapping("/ProjectManage")
	public String ProjectManage() {
		return "ProjectManage.html";
	}
}
