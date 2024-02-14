package com.mysite.Soda.selectProfile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchMemberVO {
	private String profile_image;
	private String member_name;
	private String company_name;
	private String email;
	private String phone_num;
	private String status_msg;
}
