package com.mysite.Soda.selectFeed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AllFeedSelectVO {
	private int feed_id;
	private int project_id;
	private int type;
	private int reply_cnt;
	private int likey_cnt;
}
