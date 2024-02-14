package com.mysite.Soda.deleteFeed;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteFeedScheduleService {
	
	private final DeleteFeedScheduleDAO deletefeedscheduledao;
	
	public void deletefeed(int feed_id) {
		deletefeedscheduledao.deletefeed(feed_id);
	}
}
