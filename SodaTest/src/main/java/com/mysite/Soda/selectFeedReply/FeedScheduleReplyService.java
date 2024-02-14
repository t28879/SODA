package com.mysite.Soda.selectFeedReply;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedScheduleReplyService {
	
	private final FeedScheduleReplyDAO feedschedulereplydao;
	
	public List<FeedScheduleReplyVO> projectID(int project_id) {
		return feedschedulereplydao.projectID(project_id);
	}
}
