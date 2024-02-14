package com.mysite.Soda.selectProfile;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import groovy.transform.builder.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonAutoDetect
public class SearchMemberDTO {
	private int member_id;
	
	public SearchMemberDTO() {
		
	}
	
	@Builder
	public SearchMemberDTO(int member_id) {
		this.member_id = member_id;
	}
}
