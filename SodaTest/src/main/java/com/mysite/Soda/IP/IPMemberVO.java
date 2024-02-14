package com.mysite.Soda.IP;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IPMemberVO {
	private int member_id;
	private int ipformem;
	private String name;
	private String email;
	private LocalDateTime allow_date;
	private int company_id;
}
