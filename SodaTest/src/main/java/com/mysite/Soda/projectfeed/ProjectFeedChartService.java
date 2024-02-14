package com.mysite.Soda.projectfeed;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectFeedChartService {
	
	private final ProjectFeedChartDAO projectfeedchartdao;
	
	public List<Integer> projectID(int project_id) {
		return projectfeedchartdao.projectID(project_id);
	}
}