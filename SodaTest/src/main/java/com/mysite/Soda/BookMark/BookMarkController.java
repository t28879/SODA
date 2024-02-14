package com.mysite.Soda.BookMark;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mysite.Soda.MainService;
import com.mysite.Soda.color.ColorVO;
import com.mysite.Soda.folder.FolderVO;
import com.mysite.Soda.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookMarkController {
	
	private final BookMarkService bookmarkservice;
	private final MainService mainservice;
	private final HttpSession session;
	@GetMapping("/BookMarkDH")
	public String BookMarkDH(Model model) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		List<FeedTypeVO> bookmarklist = bookmarkservice.getbookmark(member_id);
		model.addAttribute("bookmarklist",bookmarklist);
		
		List<ColorVO> color = mainservice.getColor(member_id);
		model.addAttribute("projectWithColor", color);
		
		List<FolderVO> folder = mainservice.getFolder(member_id);
		model.addAttribute("folder", folder);
		MemberVO member = mainservice.getMember(member_id); 
		model.addAttribute("member",member);
		return "BookMark.html";
	}
	
}
