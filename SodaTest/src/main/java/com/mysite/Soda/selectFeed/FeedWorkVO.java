package com.mysite.Soda.selectFeed;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FeedWorkVO {
	private String profile_image;
	private String member_name;
	private Date regist_date;
	private String title;
	private int feed_id;
	private int process;
	private Date start_date;
	private Date deadline;
	private int priority;
	private int progress;
	private int sub_process;
	private String sub_title;
	private Date sub_deadline;
	private int sub_priority;
	private String content;
	private int member_id;
	private int sub_task_id;
}
