package com.mysite.Soda.member;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberVO {
	private int member_id;
	private String name;
	private String phone_num;
	private String pw;
	private String email;
	private String job_name;
	private int sub_department_id;
	private int usage_status;
	private String profile_image;
	private int push_onoff;
	private int ip_onoff;
	private int company_id;
	private LocalDateTime join_date;
	private int department_id;
	private String status_msg;
	private int company_manager;
	private String memo;
	private int ok;
}
