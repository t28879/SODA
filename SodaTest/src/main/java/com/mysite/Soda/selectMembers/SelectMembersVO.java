package com.mysite.Soda.selectMembers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SelectMembersVO {
	private String member_name;
	private String company_name;
	private int admin;
	private String profile_image;
	private int member_id;
}
