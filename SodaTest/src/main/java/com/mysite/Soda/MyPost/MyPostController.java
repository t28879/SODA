package com.mysite.Soda.MyPost;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mysite.Soda.MainService;
import com.mysite.Soda.BookMark.FeedTypeVO;
import com.mysite.Soda.color.ColorVO;
import com.mysite.Soda.folder.FolderVO;
import com.mysite.Soda.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyPostController {
	
	private final MyPostService mypostservice;
	private final HttpSession session;
	private final MainService mainservice;
	@GetMapping("/MyPost")
	public String MyPost(Model model) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		List<FeedTypeVO> wholelist = mypostservice.getmyposts(member_id);
		Collections.sort(wholelist, Comparator.comparing(FeedTypeVO::getRegist_date));
		model.addAttribute("wholelist",wholelist);
		List<ColorVO> color = mainservice.getColor(member_id);
		model.addAttribute("projectWithColor", color);
		
		List<FolderVO> folder = mainservice.getFolder(member_id);
		model.addAttribute("folder", folder);
		
		MemberVO member = mainservice.getMember(member_id); 
		model.addAttribute("member",member);
		return "MyPost.html";
	}
	
}
