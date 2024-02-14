package com.mysite.Soda.Join;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.Soda.DTO.CompanyDTO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/SODA")
public class EnterCompanyController {

	private final JoinService joinService;
	private final FindCompanyService findCompanyService;
	
	@GetMapping("/EnterCompany")
	public String enterPage() {
		return "SODA_EnterCompany";
	}
	
	
	@PostMapping("/EnterCompany")
	public String EnterCompany(@RequestParam(name="companyId") CompanyDTO companyId, @RequestParam(name="joinSignUpEmail") String email,
							   @RequestParam(name="joinSignUpName") String name, @RequestParam(name="joinSignUpPw") String pw, HttpSession session) {
		boolean enter = joinService.enterMember(companyId, email, name, pw);
		
		Integer memberId = joinService.getMemberId(email);
		if(enter) {
			session.setAttribute("memberId",memberId);
			return "redirect:/SODA_DASHBOARD";
		} else {
			return "redirect:/SODA/EnterCompany?error=existEnterEmail";
		}
	}
	
	@PostMapping("/EnterCom")
	@ResponseBody
	public String EnterCom(@RequestParam(name="joinComName") String name) {
		String company = findCompanyService.findComName(name);
		return company;
	}
	
	@PostMapping("/Enter")
	public String Enter(@RequestParam(name="joinComName") String name, Model model2) {
		Integer companyId = findCompanyService.getCompanyId(name);
		boolean enterCom = findCompanyService.findCompany(name);
		CompanyDTO enterComId = new CompanyDTO();
		enterComId.setCompanyId(companyId);
		
		if(enterCom) {
			model2.addAttribute("companyId",enterComId);
			return "SODA_EnterCompany";
		} else {
			return "SODA_EnterMember";
		}
	}
}
