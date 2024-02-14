package com.mysite.Soda.Join;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.Soda.DTO.JoinMember;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/SODA")
public class JoinController {

	private final JoinService joinService;
	
	@GetMapping("/JoinSelect")
	public String joinSelect() {
		return "SODA_JoinSelect";
	}
	@GetMapping("/EnterMember")
	public String joinPage() {
		return "SODA_EnterMember";
	}
	@GetMapping("/CreateMember")
	public String createMember() {
		return "SODA_CreateMember";
	}
	
	@PostMapping("/SearchEmail")
	@ResponseBody
	public String SearchEmail(@RequestParam(name="signUpEmail") String signUpEmail) {
		String userEmail = joinService.memberEmail(signUpEmail);
		return userEmail;
	}
	
	@PostMapping("/joinSearchEmail")
	@ResponseBody
	public String joinSearchEmail(@RequestParam(name="joinSignUpEmail") String joinSignUpEmail) {
		String userEmail = joinService.memberEmail(joinSignUpEmail);
		return userEmail;
	}
	
	@PostMapping("/Join")
	public String Join(@RequestParam(name="newSignUpEmail") String email, @RequestParam(name="newSignUpName") String name,
					   @RequestParam(name="newSignUpPw") String pw, Model model) {
		boolean join = joinService.joinMember(email, name, pw);
		String memberEmail = joinService.memberEmail(email);
		JoinMember JoinEmail = new JoinMember();
		JoinEmail.setEmail(memberEmail);
		if(join) {
			model.addAttribute("memberEmail",JoinEmail);
			return "SODA_CreateCompany";
		} else {
			return "SODA_CreateMember";
		}
	}
	
	
}
