package com.mysite.Soda;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/SODA")
@Controller
public class HomeController {
	
	@GetMapping("/Home")
	public String Home() {
		return "SODA_Home";
	}
	
	@GetMapping("/Func")
	public String Func() {
		return "SODA_Function";
	}
	
	@GetMapping("/Price")
	public String Price() {
		return "SODA_Price";
	}
	
	@GetMapping("/TeamCorpo")
	public String TeamCorpo() {
		return "SODA_TeamCorpo";
	}
}
