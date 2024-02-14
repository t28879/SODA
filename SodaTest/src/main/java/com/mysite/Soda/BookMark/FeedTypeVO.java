package com.mysite.Soda.BookMark;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FeedTypeVO {
	
	public FeedTypeVO() {
	}
	private String title;
	private String projectname;
	private LocalDateTime regist_date;
	private String writer;
	private int type;
	private int project_id;
}
