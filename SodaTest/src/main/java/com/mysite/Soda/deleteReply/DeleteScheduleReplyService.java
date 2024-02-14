package com.mysite.Soda.deleteReply;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteScheduleReplyService {

	private final DeleteScheduleReplyDAO deleteschedulereplydao;

	public int thisMember(int reply_id, int userNum) {
		return deleteschedulereplydao.thisMember(reply_id, userNum);
	}

	public void deletereply(int reply_id, int userNum, int feed_id) {
		deleteschedulereplydao.deletereply(reply_id, userNum);
		deleteschedulereplydao.updatereplycnt(feed_id);
	}
	
}
