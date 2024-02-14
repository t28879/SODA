package com.mysite.Soda.project;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.Soda.MainService;
import com.mysite.Soda.color.ColorVO;
import com.mysite.Soda.folder.FolderVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectController {
	private final ProjectService projectservice;
	private final HttpSession session;
	private final MainService mainservice;
	
	@GetMapping("/getallproject")
	public String GetAllProjectById(Model model){
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		List<ProjectinfoVO> projectlists = projectservice.findAllProject(member_id);
		model.addAttribute("projects",projectlists);
		
		List<ColorVO> color = mainservice.getColor(member_id);
		model.addAttribute("projectWithColor", color);
		
		List<FolderVO> folder = mainservice.getFolder(member_id);
		model.addAttribute("folder", folder);
		return "ProjectManage.html";
	}
	
	@GetMapping("/getprojectadmin")
	@ResponseBody
	public List<ProjectAdminVO> GetProjectAdmin(@RequestParam(name = "project_id")int project_id) {
		return projectservice.getProjectAdmin(project_id);
	}
	
	@GetMapping("/getprojectinfo")
	@ResponseBody
	public ProjectVO GetProjectInfo(@RequestParam(name = "project_id")int project_id) {
		return projectservice.getProjectInfo(project_id);
	}
	
	@GetMapping("/getpremanagerlist")
	@ResponseBody
	public List<ProjectMemberVO> GetPreManagerList(@RequestParam(name = "project_id")int project_id){
		return projectservice.getpremanager(project_id);
	}
	
}
