package com.mysite.Soda.Statistic;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConnectionVO {
	private String name;
	private String deptname;
	private String job_name;
	private String email;
	private String phone_num;
	private LocalDateTime join_date;
	private int count;
	private int pcount;
	private int cmtcount;
	private int crcount;
	private int msgcount;
	
}
