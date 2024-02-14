package com.mysite.Soda.selectMembers;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SelectMembersService {
	
	private final SelectMembersDAO selectmembersdao;
	
	public List<SelectMembersVO> search(String searchInput, int project_id) {
		return selectmembersdao.search(searchInput, project_id);
	}
}
