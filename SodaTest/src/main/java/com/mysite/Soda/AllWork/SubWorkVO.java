package com.mysite.Soda.AllWork;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubWorkVO {
	
	public SubWorkVO() {
	}
	private int feed_id;
	private int sub_task_id;
	private String name;
	private int sub_process;
	private Date deadline;
	private Date start_date;
	private int progress;
	private int member_id;
	private int sub_priority;
	
}
