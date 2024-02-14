package com.mysite.Soda.selectFeed;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedTodoService {
	
	private final FeedTodoDAO feedtododao;
	
	public List<FeedTodoVO> projectID(int project_id) {
		return feedtododao.projectID(project_id);
	}
}
