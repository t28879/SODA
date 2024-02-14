package com.mysite.Soda.selectFeed;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedTextService {
	
	private final FeedTextDAO feedtextdao;
	
	public List<FeedTextVO> projectID(int project_id) {
		return feedtextdao.projectID(project_id);
	}
}
