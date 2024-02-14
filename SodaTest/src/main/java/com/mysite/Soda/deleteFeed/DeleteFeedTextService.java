package com.mysite.Soda.deleteFeed;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteFeedTextService {
	
	private final DeleteFeedTextDAO deletefeedtextdao;
	
	public void deletefeed(int feed_id) {
		deletefeedtextdao.deletefeed(feed_id);
	}
}
