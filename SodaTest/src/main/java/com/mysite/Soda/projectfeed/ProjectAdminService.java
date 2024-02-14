package com.mysite.Soda.projectfeed;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectAdminService {
	
	private final ProjectAdminDAO projectadmindao;
	
	public List<ProjectAdminVO> projectID(int project_id) {
		return projectadmindao.projectID(project_id);
	}
}
