package com.mysite.Soda.selectFeed;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FeedScheduleVO {
	private String profile_image;
	private String name;
	private Date regist_date;
	private Date start_date;
	private Date deadline;
	private String title;
	private String content;
	private int go;
	private int no;
	private int thinking;
	private int feed_id;
	private int member_id;
}
