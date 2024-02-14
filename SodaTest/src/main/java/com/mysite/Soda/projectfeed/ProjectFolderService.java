package com.mysite.Soda.projectfeed;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectFolderService {
	
	private final ProjectFolderDAO projectfolderdao;
	
	public List<ProjectFolderVO> projectID(int project_id) {
		return projectfolderdao.project_id(project_id);
	}
}
