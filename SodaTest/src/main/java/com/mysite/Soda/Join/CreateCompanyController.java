package com.mysite.Soda.Join;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.Soda.DTO.CompanyDTO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/SODA")
@RequiredArgsConstructor
public class CreateCompanyController {
	
	private final CreateCompanyService createCompanyService;
	private final FindCompanyService findCompanyService;
	
	@GetMapping("/createCompanyPage")
	public String CrateCompany() {
		return "SODA_CreateCompany"; 
	}
	
	@PostMapping("/SearchCom")
	@ResponseBody
	public CompanyDTO SearchCom(@RequestParam(name="comName") String name) {
		String company = findCompanyService.findComName(name);
		CompanyDTO searchComName = new CompanyDTO();
		searchComName.setName(company);
		return searchComName;
	}
	
	@PostMapping("/CreateCompany")
	public String createCompany(@RequestParam(name="userId") String email, @RequestParam(name="comName") String name, 
								@RequestParam(name="comURL") String url, HttpSession session) {
		Integer memberId = createCompanyService.createCompany(email, name, url);
		if(memberId != null) {
			session.setAttribute("memberId", memberId);
			return "redirect:/SODA_DASHBOARD";
		} else {
			return "SODA_CreateCompany";
		}
		
	}
}
