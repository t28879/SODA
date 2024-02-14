package com.mysite.Soda.updateFeed;

import java.sql.Date;

import groovy.transform.builder.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFeedScheduleDTO {
	private int feed_id;
	private String scheduleModifyTitle;
	private Date scheduleModifyStart;
	private Date scheduleModifyEnd;
	private String scheduleModifyContent;
	
	public UpdateFeedScheduleDTO() {
		
	}
	
	@Builder
	public UpdateFeedScheduleDTO(int feed_id, String scheduleModifyTitle,
								 Date scheduleModifyStart, String scheduleModifyContent) {
		this.feed_id = feed_id;
		this.scheduleModifyTitle = scheduleModifyTitle;
		this.scheduleModifyStart = scheduleModifyStart;
		this.scheduleModifyContent = scheduleModifyContent;
	}
}
