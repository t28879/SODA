package com.mysite.Soda.deleteFeedPro;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteFeedProService {
	
	private final DeleteFeedProDAO deletefeedprodao;
	
	public boolean thisAdmin(int project_id, int userNum) {
	    return deletefeedprodao.thisAdmin(project_id, userNum);
	}
	
	public void deleteFeedProInAdmin(int project_id, int userNum) {
		deletefeedprodao.deletrPro(project_id, userNum);
	}
}
