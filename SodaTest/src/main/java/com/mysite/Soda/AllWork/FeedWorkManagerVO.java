package com.mysite.Soda.AllWork;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FeedWorkManagerVO {
	
	public FeedWorkManagerVO() {
	}
	private int worlmanagerid;
	private String worlmanagername;
	private String email;
	private String deptname;
}
