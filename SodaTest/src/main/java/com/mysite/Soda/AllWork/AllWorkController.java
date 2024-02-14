package com.mysite.Soda.AllWork;

import java.util.List;

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
public class AllWorkController {
	
	private final AllWorkService allworkservice;
	private final MainService mainservice;
	private final HttpSession session;
	@GetMapping("/AllWork")
	public String GetAllWork(Model model) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		List<FinalWorkProjectVO> allwork = allworkservice.getworklist(member_id);
		model.addAttribute("allwork",allwork);
		List<ColorVO> color = mainservice.getColor(member_id);
		model.addAttribute("projectWithColor", color);
		
		List<FolderVO> folder = mainservice.getFolder(member_id);
		model.addAttribute("folder", folder);
		
		MemberVO member = mainservice.getMember(member_id); 
		model.addAttribute("member",member);
		return "AllWork.html";
	}
	@PostMapping("/UpdateFeedProcess")
	@ResponseBody
	public String UpdateFeedProcess(
			@RequestParam(name="selectfeedid") int feed_id,
			@RequestParam(name="type") int type,
			@RequestParam(name="selectedimg") int process) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		return allworkservice.updateprocess(feed_id, process,type,member_id);
	}
	@PostMapping("/UpdateFeedPriority")
	@ResponseBody
	public String UpdateFeedPriority(
			@RequestParam(name="selectfeedid") int feed_id,
			@RequestParam(name="type") int type,
			@RequestParam(name="selectedimg") int priority) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		return allworkservice.updatepriority(feed_id, priority,type,member_id);
	}
	@PostMapping("/UpdateFeedProgress")
	@ResponseBody
	public String UpdateFeedProgress(
			@RequestParam(name="selectfeedid") int selectfeedid,
			@RequestParam(name="percentage") int percentage,
			@RequestParam(name="type") int type) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		return allworkservice.updatefeedprogress(selectfeedid, percentage, type,member_id);
	}
	@GetMapping("/GetWorkManager")
	@ResponseBody
	public List<FeedWorkManagerVO> GetWorkManager(
			@RequestParam(name="feedorsubID") int feedorsubID,
			@RequestParam(name="type") int type) {
		return allworkservice.getmanagerlist(feedorsubID, type);
	}
	@GetMapping("/GetPreWorkManager")
	@ResponseBody
	public List<FeedWorkManagerVO> GetPreWorkManager(
			@RequestParam(name="feedorsubID") int feedorsubID,
			@RequestParam(name="type") int type) {
		return allworkservice.getpremanagerlist(feedorsubID, type);
	}
	
	
}
