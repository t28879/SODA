package com.mysite.Soda.Calendar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.Soda.MainService;
import com.mysite.Soda.color.ColorVO;
import com.mysite.Soda.folder.FolderVO;
import com.mysite.Soda.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CalendarController {
	
	private final CalendarService cl;
	private final HttpSession session;
	private final MainService mainservice;
	
	@GetMapping("/Calendar")
	public String GetAllCompanyMember(Model model) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		List<ColorVO> color = mainservice.getColor(member_id);
		model.addAttribute("projectWithColor", color);
		
		List<FolderVO> folder = mainservice.getFolder(member_id);
		model.addAttribute("folder", folder);
		MemberVO member = mainservice.getMember(member_id); 
		model.addAttribute("member",member);
		return "Calendar.html";
	}
	
	@GetMapping("/TestCalendar")
	@ResponseBody
	public List<Map<String, Object>> monthPlan() {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		List<CalendarVO> list = cl.tt(member_id);
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		HashMap<String, Object> hash = new HashMap<String, Object>();		
		for(int i=0; i < list.size(); i++) {			
			hash.put("title", list.get(i).getTitle()); //제목
			hash.put("start", list.get(i).getStart()); //시작일자
			hash.put("end", list.get(i).getEnd()); //종료일자
			jsonObj = new JSONObject(hash); //중괄호 {key:value , key:value, key:value}
			jsonArr.add(jsonObj); // 대괄호 안에 넣어주기[{key:value , key:value, key:value},{key:value , key:value, key:value}]
		}
		
		List<IndividualVO> indilist = cl.individual(member_id);
		for(int i=0; i < indilist.size(); i++) {			
			hash.put("title", indilist.get(i).getTitle()); //제목
			hash.put("start", indilist.get(i).getStart()); //시작일자
			hash.put("individual", indilist.get(i).getIndividualid());// PK 넣어주기
			jsonObj = new JSONObject(hash); //중괄호 {key:value , key:value, key:value}
			jsonArr.add(jsonObj); // 대괄호 안에 넣어주기[{key:value , key:value, key:value},{key:value , key:value, key:value}]
		}
		return jsonArr;
	}
	@PostMapping("/UpdateIndividualSchedule")
	@ResponseBody
	public int UpdateIndividualSchedule(
			@RequestParam(name = "date")String date,
			@RequestParam(name = "hour")String hour,
			@RequestParam(name = "min")String min,
			@RequestParam(name = "title")String title) {
		int curval = cl.updateindischedule(date, hour, min, title);
		return curval;
	}
	@PostMapping("/DeleteIndividualSchedule")
	public ResponseEntity<Void> DeleteIndividualSchedule(
			@RequestParam(name = "individualID")int individualID) {
		cl.deleteindischedule(individualID);
		return  ResponseEntity.ok().build();
	}
	
}
