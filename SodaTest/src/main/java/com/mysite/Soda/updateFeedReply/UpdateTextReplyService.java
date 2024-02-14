package com.mysite.Soda.updateFeedReply;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateTextReplyService {
	
	private final UpdateTextReplyDAO updatetextreplydao;
	
	public int thisMember(int reply_id, int userNum) {
		return updatetextreplydao.thisMember(reply_id, userNum);
	}
	
	public void updatereply(int reply_id, String modifyInput, int userNum) {
		updatetextreplydao.updatereply(reply_id, modifyInput, userNum);
	}
}
