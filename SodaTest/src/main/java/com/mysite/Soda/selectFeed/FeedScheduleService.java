package com.mysite.Soda.selectFeed;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedScheduleService {
	
	private final FeedScheduleDAO feedscheduledao;
	
	public List<FeedScheduleVO> projectID(int project_id) {
		return feedscheduledao.projectID(project_id);
	}
}
