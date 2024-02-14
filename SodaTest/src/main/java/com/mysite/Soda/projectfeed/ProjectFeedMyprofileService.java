package com.mysite.Soda.projectfeed;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectFeedMyprofileService {
	
	private final ProjectFeedMyprofileDAO projectfeedmyprofiledao;
	
	public ProjectFeedMyprofileVO memberID(int member_id) {
		return projectfeedmyprofiledao.memberID(member_id);
	}
}
