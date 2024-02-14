package com.mysite.Soda.Company;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CompanyController {
	
	private final CompanyService companyservice;
	private final HttpSession session;
	@GetMapping("/CompanyInfo")
	public String CompanyInfo(Model model) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		CompanyVO companyInfo = companyservice.getCompanyInfo(member_id); 
		model.addAttribute("company",companyInfo);
		return "CompanyInfo.html";
	}
	
	@GetMapping("/updateCompanyName")
	@ResponseBody 
	public String updateCompanyName(@RequestParam(name = "value") String newcmpName,@RequestParam(name = "userNum") int memberId){
		companyservice.updateNewName(newcmpName,memberId);
		return "SUCCESS";
	}
	
	@GetMapping("/updateCompanyUrl")
	@ResponseBody 
	public String updateCompanyUrl(@RequestParam(name = "value") String newcmpUrl,@RequestParam(name = "userNum") int memberId){
		companyservice.updateNewUrl(newcmpUrl,memberId);
		return "SUCCESS";
	}
		
}