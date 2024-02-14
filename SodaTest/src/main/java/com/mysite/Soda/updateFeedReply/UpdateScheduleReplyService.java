package com.mysite.Soda.updateFeedReply;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateScheduleReplyService {

	private final UpdateScheduleReplyDAO updateschedulereplydao;

	public int thisMember(int reply_id, int userNum) {
		return updateschedulereplydao.thisMember(reply_id, userNum);
	}

	public void updatereply(int reply_id, String scheduleVal, int userNum) {
		updateschedulereplydao.updatereply(reply_id, scheduleVal, userNum);
	}
}
