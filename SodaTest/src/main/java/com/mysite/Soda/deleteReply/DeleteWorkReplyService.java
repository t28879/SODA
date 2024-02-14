package com.mysite.Soda.deleteReply;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteWorkReplyService {
	
	private final DeleteWorkReplyDAO deleteworkreplydao;
	
	public int thisMember(int reply_id, int userNum) {
		return deleteworkreplydao.thisMember(reply_id, userNum);
	}
	
	public void deletereply(int reply_id, int userNum, int feed_id) {
		deleteworkreplydao.deletereply(reply_id, userNum);
		deleteworkreplydao.updatereplycnt(feed_id);
	}
}
