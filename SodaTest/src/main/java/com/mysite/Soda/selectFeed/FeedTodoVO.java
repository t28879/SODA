package com.mysite.Soda.selectFeed;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FeedTodoVO {
	private String profile_image;
	private String name;
	private Date regist_date;
	private String title;
	private int feed_id;
	private int finish;
	private String content;
	private Date deadline;
	private int member_id;
}
