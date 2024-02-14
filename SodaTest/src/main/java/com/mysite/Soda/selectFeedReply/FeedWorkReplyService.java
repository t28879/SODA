package com.mysite.Soda.selectFeedReply;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedWorkReplyService {
	
	private final FeedWorkReplyDAO feedworkreplydao;
	
	public List<FeedWorkReplyVO> projectID(int project_id) {
		return feedworkreplydao.projectID(project_id);
	}
}
