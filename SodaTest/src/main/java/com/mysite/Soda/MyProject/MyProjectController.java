package com.mysite.Soda.MyProject;

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
public class MyProjectController {
	
	private final MyProjectService myprojectservice;
	private final HttpSession session;
	private final MainService mainservice;
	@GetMapping("/MyProject")
	public String GetMyProject(Model model) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		List<MyProjectVO> projectlist =  myprojectservice.getMyProject(member_id);
		model.addAttribute("projectlist",projectlist);
		List<MyProjectFolderVO> folderlist = myprojectservice.getprojectfolderlist(member_id);
		model.addAttribute("folderlist",folderlist);
		List<ColorVO> color = mainservice.getColor(member_id);
		model.addAttribute("projectWithColor", color);
		
		List<FolderVO> folder = mainservice.getFolder(member_id);
		model.addAttribute("folder", folder);
		MemberVO member = mainservice.getMember(member_id); 
		model.addAttribute("member",member);
		
		return "MyProject.html";
	}
	
	
}
