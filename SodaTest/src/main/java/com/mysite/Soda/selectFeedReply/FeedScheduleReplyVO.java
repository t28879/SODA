package com.mysite.Soda.selectFeedReply;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FeedScheduleReplyVO {
	private String profile_image;
	private String contents;
	private Date reply_date;
	private String name;
	private int feed_id;
	private int reply_id;
	private int member_id;
}
