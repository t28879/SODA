package com.mysite.Soda.CompanyMember;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CompanyMemberController {
	private final CompanyMemberService companymemberservice;
	private final HttpSession session;
	@GetMapping("/WorkerManage")
	public String GetAllCompanyMember(Model model) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		List<CompanyMemberVO> companymembers = companymemberservice.getallcmpmem(member_id);
		model.addAttribute("companymembers",companymembers);
		
		return "WorkerManage.html";
	}
	
	@GetMapping("/WorkerManage/modify")
	@ResponseBody 
	public String modifycompanymember(
			@RequestParam(name = "user_email")String email,
			@RequestParam(name = "name")String name,
			@RequestParam(name = "dept_name")String dept_name,
			@RequestParam(name = "job_name")String job_name,
			@RequestParam(name = "phone_num")String phone_num){
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		companymemberservice.modifymember(member_id,email, name,job_name, phone_num);
		companymemberservice.modifydept(member_id,email, dept_name);
		
		return "Success";
	}
	@GetMapping("/WorkerManage/banor")
	@ResponseBody 
	public String banorremember(
			@RequestParam(name = "member_id")int m,
			@RequestParam(name = "Banor")int Banor) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		companymemberservice.banorremember(m, Banor,member_id);
		
		return "Success";
	}
	
	@GetMapping("/WorkerManage/modifymanager")
	@ResponseBody 
	public String modifymanager(
			@RequestParam(name = "member_id")int m,
			@RequestParam(name = "YorN")int YorN) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		companymemberservice.modifymanager(m, YorN,member_id);
		
		return "Success";
	}
	
}
