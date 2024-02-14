package com.mysite.Soda.MyProject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.Soda.color.ColorDAO;
import com.mysite.Soda.project.ProjectVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyProjectService {
	
	private final MyProjectDAO myprojectdao;
	private final ColorDAO colordao;
	
	public List<MyProjectVO> getMyProject(int member_id){
		List<MyProjectVO> arr = new ArrayList<MyProjectVO>();
		List<ProjectVO> projectlist = myprojectdao.getmyprojectinfo(member_id);
		for(ProjectVO pv : projectlist) {
			MyProjectVO mv = new MyProjectVO();
			mv.setProject_id(pv.getProject_id());
			mv.setName(pv.getName());
			mv.setColor(colordao.getColorType(pv.getColor_id()).getType());
			arr.add(mv);
		}
		
		return arr;
	}
	public void updatemyproeject(MyProjectColorChange MyProjectColorChange){
		int color = MyProjectColorChange.getSelectedColor();
		List<Integer> project_id = MyProjectColorChange.getSelectedDivs();
		for(int i : project_id) {
			 myprojectdao.updatemyproject(color, i); 
		}
	}
	public void updatemyproejectfolder(MyProjectFolderChange MyProjectFolderChange){
		List<Integer> projectlist = MyProjectFolderChange.getSelectedDivs();
		List<Integer> folderlist = MyProjectFolderChange.getSelectedFolder();
		for(int project_id : projectlist) {
			for(int folder_id : folderlist) {
				if(!myprojectdao.searchmyprojectfolder(folder_id,project_id)) {
					myprojectdao.insertmyprojectfolder(folder_id, project_id);
				}
			}
		}
	}
	public List<MyProjectFolderVO> getprojectfolderlist(int member_id){
		return myprojectdao.getmyprojectfolderlist(member_id);
	}
	
}
