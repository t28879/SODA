package com.mysite.Soda.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.Soda.SearchMemDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
	
	private final ProjectDAO projectdao;
	private final SearchMemDAO searchmemdao;
	
	public List<ProjectinfoVO> findAllProject(int member_id){
		return projectdao.findAllProject(member_id);
	}
	public List<ProjectAdminVO> getProjectAdmin(int project_id){
		return projectdao.getAdminList(project_id);
	}
	public ProjectVO getProjectInfo(int project_id) {
		return projectdao.getprojectinfo(project_id);
	}
	public List<ProjectMemberVO> getpremanager(int project_id){
		return projectdao.getpremanager(project_id);
	}
	public void updateAdmin(int master_id,AdminList AdminList) {
		String change = "";
		String full = "";
		String projectname = AdminList.getProjectname();
		List<Long> adlist = AdminList.getAdList();
		int project_id = AdminList.getProject_id();
		List<ProjectAdminListVO> originallist = projectdao.getoriginalList(project_id);
		for(ProjectAdminListVO manager : originallist ) {
			Long member_id = manager.getMember_id();
			if(!adlist.contains(member_id)) {
				full = searchmemdao.searchmeminfoLongtype(member_id).getName()+
					   "("+searchmemdao.searchmeminfoLongtype(member_id).getEmail()+")";
				change = "관리자 삭제";
				projectdao.AdminChangeLog(master_id, full, change);
			}
		}
		List<Long> orilist = new ArrayList<Long>();
		for(int t = 0 ; t < originallist.size() ; t++) {
			orilist.add(originallist.get(t).getMember_id());
		}
		for(int i = 0 ; i<adlist.size(); i++) {
			Long member_id = adlist.get(i);
			if(!orilist.contains(member_id)) {
				full = searchmemdao.searchmeminfoLongtype(member_id).getName()+
						   "("+searchmemdao.searchmeminfoLongtype(member_id).getEmail()+")";
				change = "관리자 추가";
				projectdao.AdminChangeLog(master_id, full, change);
			}
		}
		
		  projectdao.refreshprojectmember(project_id);//다 0으로 만들고
		  projectdao.updateprojectname(projectname,project_id); 
		  for(Long member_id :adlist) { 
			  projectdao.updateprojectAdmin(member_id, project_id); 
			  }
		 
		 
	}
}
