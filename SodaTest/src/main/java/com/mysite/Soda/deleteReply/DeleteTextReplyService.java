package com.mysite.Soda.deleteReply;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteTextReplyService {
	
	private final DeleteTextReplyDAO deletetextreplydao;
	
	public int thisMember(int reply_id, int userNum) {
		return deletetextreplydao.thisMember(reply_id, userNum);
	}
	
	public void deletereply(int reply_id, int userNum, int feed_id) {
		deletetextreplydao.deletereply(reply_id, userNum);
		deletetextreplydao.updatefeedtexreplycnt(feed_id);
	}
}
