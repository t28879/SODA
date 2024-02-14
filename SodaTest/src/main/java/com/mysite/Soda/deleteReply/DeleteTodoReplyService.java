package com.mysite.Soda.deleteReply;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteTodoReplyService {
	
	private final DeleteTodoReplyDAO deletetodoreplydao;
	
	public int thisMember(int reply_id, int userNum) {
		return deletetodoreplydao.thisMember(reply_id, userNum);
	}
	
	public void deletereply(int reply_id, int userNum, int feed_id) {
		deletetodoreplydao.deletereply(reply_id, userNum);
		deletetodoreplydao.updatereplycnt(feed_id);
	}
}
