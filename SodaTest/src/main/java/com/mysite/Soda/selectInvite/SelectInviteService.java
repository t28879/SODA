package com.mysite.Soda.selectInvite;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SelectInviteService {
	
	private final SelectInviteDAO selectinvitedao;
	
	public List<SelectInviteVO> projectID(int project_id, int userNum) {
		return selectinvitedao.projectID(project_id, userNum);
	}
}
