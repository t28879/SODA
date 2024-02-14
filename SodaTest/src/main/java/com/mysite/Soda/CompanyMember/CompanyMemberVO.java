package com.mysite.Soda.CompanyMember;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompanyMemberVO {
	private int member_id;
	private String name;
	private String deptname;
	private String job_name;
	private String email;
	private String phone_num;
	private LocalDateTime join_date;
	private int usage_status;
	private int company_manager;
}
