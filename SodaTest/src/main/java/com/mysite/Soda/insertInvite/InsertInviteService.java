package com.mysite.Soda.insertInvite;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsertInviteService {
	
	private final InsertInviteDAO insertinvitedao;
	
	public void insertmember(int userNum, int project_id) {
		insertinvitedao.insertInvite(userNum, project_id);
	}
}
