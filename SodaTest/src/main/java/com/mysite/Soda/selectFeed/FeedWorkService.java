package com.mysite.Soda.selectFeed;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedWorkService {
	
	private final FeedWorkDAO feedworkdao;
	
	public List<FeedWorkVO> projectID(int project_id) {
		return feedworkdao.projectID(project_id);
	}
	
	public List<FeedWorkVO> getFeedDetailsById(int feed_id){
		return feedworkdao.feedID(feed_id);
	}
}
