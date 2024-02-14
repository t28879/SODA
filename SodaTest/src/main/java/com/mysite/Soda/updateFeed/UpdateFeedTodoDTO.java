package com.mysite.Soda.updateFeed;

import java.sql.Date;

import groovy.transform.builder.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFeedTodoDTO {
	private int feed_id;
	private String title;
	private Date end;
	private String content;
	
	public UpdateFeedTodoDTO() {
		
	}
	
	@Builder
	public UpdateFeedTodoDTO(int feed_id, String title, Date end, String content) {
		this.feed_id = feed_id;
		this.title = title;
		this.end = end;
		this.content = content;
	}
}
