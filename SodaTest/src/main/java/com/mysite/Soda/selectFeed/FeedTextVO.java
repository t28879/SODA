package com.mysite.Soda.selectFeed;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FeedTextVO {
	private String profile_image;
	private String name;
	private Date regist_date;
	private int feed_id;
	private String content;
	private String title;
	private int member_id;
}
