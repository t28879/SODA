package com.mysite.Soda.selectProfile;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchMemberService {
	
	private final SearchMemberDAO searchmemberdao;
	
	public SearchMemberVO searchMember(int member_id) {
	    return searchmemberdao.searchMember(member_id);
	}
}
