package com.mysite.Soda.selectFeedReply;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedTextReplyService {
	
	private final FeedTextReplyDAO feedreplydao;
	
	public List<FeedTextReplyVO> projectID(int project_id) {
		return feedreplydao.projectID(project_id);
	}
	
}
