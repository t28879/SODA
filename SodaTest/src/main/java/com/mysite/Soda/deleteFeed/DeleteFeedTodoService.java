package com.mysite.Soda.deleteFeed;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteFeedTodoService {
	
	private final DeleteFeedTodoDAO deletefeedtododao;
	
	public void deletefeed(int feed_id) {
		deletefeedtododao.deletefeed(feed_id);
	}
}
