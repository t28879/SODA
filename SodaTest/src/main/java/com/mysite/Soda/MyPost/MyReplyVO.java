package com.mysite.Soda.MyPost;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MyReplyVO {
	private String title;
	private int feedID;
	private LocalDateTime reply_date;
}
