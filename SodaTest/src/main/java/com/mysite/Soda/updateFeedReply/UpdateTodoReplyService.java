package com.mysite.Soda.updateFeedReply;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateTodoReplyService {

	private final UpdateTodoReplyDAO updatetodoreplydao;

	public int thisMember(int reply_id, int userNum) {
		return updatetodoreplydao.thisMember(reply_id, userNum);
	}

	public void updatereply(int reply_id, String todoVal, int userNum) {
		updatetodoreplydao.updatereply(reply_id, todoVal, userNum);
	}
}
