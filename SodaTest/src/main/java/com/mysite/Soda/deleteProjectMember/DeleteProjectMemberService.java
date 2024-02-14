package com.mysite.Soda.deleteProjectMember;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteProjectMemberService {
	
	private final DeleteProjectMemberDAO deleteprojectmemberdao;
	
	public boolean isAdmin(int userNum, int project_id) {
	    return deleteprojectmemberdao.isAdmin(userNum, project_id);
	}
	
	public boolean getAdmin(int project_id) {
		int adminCnt = deleteprojectmemberdao.projectAdmin(project_id);
		if(adminCnt < 2) {
			return false;
		}
		return true;
	}
	
	public int deleteProjectMember(int project_id, int userNum) {
        return deleteprojectmemberdao.deleteProjectMember(project_id, userNum);
    }
}
