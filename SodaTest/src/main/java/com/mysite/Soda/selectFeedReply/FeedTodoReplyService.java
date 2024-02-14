package com.mysite.Soda.selectFeedReply;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedTodoReplyService {
	
	private final FeedTodoReplyDAO feedtodoreplydao;
	
	public List<FeedTodoReplyVO> projectID(int project_id) {
		return feedtodoreplydao.projectID(project_id);
	}
}
