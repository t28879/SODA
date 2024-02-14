package com.mysite.Soda.updateFeedReply;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateWorkReplyService {
	
	private final UpdateWorkReplyDAO updateworkreplydao;
	
	public int thisMember(int reply_id, int userNum) {
		return updateworkreplydao.thisMember(reply_id, userNum);
	}
	
	public void updatereply(int reply_id, String workVal, int userNum) {
		updateworkreplydao.updatereply(reply_id, workVal, userNum);
	}
}
