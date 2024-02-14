package com.mysite.Soda.AllWork;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FeedWorkVO {
	
	public FeedWorkVO() {
	}
	private int project_id;
	private int feed_id;
	private int process;
	private int priority;
	private Date start_date;
	private Date deadline;
	private int progress;
	private String title;
	private String content;
	private int writer;
	private Date regist_date;
	
}
