package com.mysite.Soda.deleteFeed;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteFeedWorkService {
	
	private final DeleteFeedWorkDAO deletefeedworkdao;
	
	public void deletefeed(int feed_id) {
		deletefeedworkdao.deletefeed(feed_id);
	}
}
