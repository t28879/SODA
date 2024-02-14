package com.mysite.Soda.selectWorkmanager;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import groovy.transform.builder.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonAutoDetect
public class SelectFeedSubWorkerDTO {
	private int sub_task_id;
	
	
	public SelectFeedSubWorkerDTO() {
		
	}
	
	@Builder
	public SelectFeedSubWorkerDTO(int sub_task_id) {
		this.sub_task_id = sub_task_id;
	}
}
