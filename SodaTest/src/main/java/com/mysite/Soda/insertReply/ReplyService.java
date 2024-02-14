package com.mysite.Soda.insertReply;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {
	
	private final ReplyDAO replydao;
	
	public void replyInsert(int feed_id, int userNum, String commentText) {
		replydao.reply(feed_id, userNum, commentText);
		replydao.updateReplyCnt(feed_id);
	}
	
}
