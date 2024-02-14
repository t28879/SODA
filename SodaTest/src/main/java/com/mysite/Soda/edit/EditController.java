package com.mysite.Soda.edit;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class EditController {
	
	private final HttpSession session;
	private final EditService editService;
	
	@PostMapping("/editName")
	@ResponseBody
	public Map<String, String> editName(@RequestParam(name="userName") String name) {
		Object memIdobj = session.getAttribute("memberId");
		Integer userNum = (Integer) memIdobj;
		boolean success = editService.changeName(userNum, name);
		Map<String, String> response = new HashMap<>();
		if(success) {
			response.put("status", "success");
			response.put("message", "Edit Name Success");
		} else {
			response.put("status", "fail");
			response.put("message", "Failed Edit Name");
		}
		return response;
	}
	
	@PostMapping("/editJob")
	@ResponseBody
	public Map<String, String> editJob(@RequestParam(name="jobName") String jobName) {
		Object memIdobj = session.getAttribute("memberId");
		Integer userNum = (Integer) memIdobj;
		boolean success = editService.changeJobName(userNum, jobName);
		Map<String, String> response = new HashMap<>();
		if(success) {
			response.put("status", "success");
			response.put("message", "Edit Job Success");
		} else {
			response.put("status", "fail");
			response.put("message", "Failed Edit Job");
		}
		return response;
	}
	
	@PostMapping("/editPhone")
	@ResponseBody
	public Map<String, String> editPhone(@RequestParam(name="phoneNumber") String phoneNum) {
		Object memIdobj = session.getAttribute("memberId");
		Integer userNum = (Integer) memIdobj;
		boolean success = editService.changePhoneNum(userNum, phoneNum);
		Map<String, String> response = new HashMap<>();
		if(success) {
			response.put("status", "success");
			response.put("message", "Edit PhoneNum Success");
		} else {
			response.put("status", "fail");
			response.put("message", "Failed Edit PhoneNum");
		}
		return response;
	}
	
	@PostMapping("/editPW")
	@ResponseBody
	public Map<String, String> editPW(@RequestParam(name="currentPW") String currentPW, @RequestParam(name="newPW") String newPW) {
		Object memIdobj = session.getAttribute("memberId");
		Integer userNum = (Integer) memIdobj;
		System.out.println("controller:" + currentPW);
		System.out.println("controller:" + newPW);
		boolean success = editService.changePW(userNum, currentPW, newPW);
		Map<String, String> response = new HashMap<>();
		if(success) {
			response.put("status", "success");
			response.put("message", "Edit PW Success");
		} else if(success==false) {
			response.put("status", "fail");
			response.put("message", "Failed Edit PW");
		}
		return response;
	}
	
	@PostMapping("/editCom")
	@ResponseBody
	public String findCom() {
		Object memIdobj = session.getAttribute("memberId");
		Integer userNum = (Integer) memIdobj;
		System.out.println("editCon:"+userNum);
		String comName = editService.findComName(userNum);
		System.out.println("editCon: " + comName);
		return comName;
	}
	
//	@PostMapping("/uploadProfile")
//	@ResponseBody
//	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
//		Object memIdobj = session.getAttribute("memberId");
//		Integer userNum = (Integer) memIdobj;
//		boolean success = editService.editImage(userNum, file);
//		
//		if(success) {
//			return ResponseEntity.ok().body("success");
//		} else {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed");
//		}
//	}
	
	@PostMapping("/uploadProfile")
	@ResponseBody
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
		Object memIdobj = session.getAttribute("memberId");
		Integer userNum = (Integer) memIdobj;
		String imageUrl = editService.editImage(userNum, file);
		System.out.println("eidtCon:" + imageUrl);
		if(imageUrl != null) {
			return ResponseEntity.ok().body(imageUrl);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed");
		}
	}
	
}
