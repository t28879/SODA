package com.mysite.Soda.projectfeed;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectColorService {
	
	private final ProjectColorDAO projectcolordao;
	
	public ProjectColorVO projectID(int project_id) {
		return projectcolordao.projectID(project_id);
	}
}
