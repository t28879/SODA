package com.mysite.Soda;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberLongTypeVO {
	private Long member_id;
	private String name;
	private String deptname;
	private String jobname;
	private String email;
	private int company_id;
	private String phone_num;
}
