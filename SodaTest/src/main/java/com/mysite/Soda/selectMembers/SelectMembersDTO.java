package com.mysite.Soda.selectMembers;

import groovy.transform.builder.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectMembersDTO {
	private String searchMember;
	
	public SelectMembersDTO() {
		
	}
	
	@Builder
	public SelectMembersDTO(String searchMember) {
		this.searchMember = searchMember;
	}
}
