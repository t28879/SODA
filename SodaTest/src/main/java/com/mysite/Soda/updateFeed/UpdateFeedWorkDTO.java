package com.mysite.Soda.updateFeed;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import groovy.transform.builder.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonAutoDetect
public class UpdateFeedWorkDTO {
	private int feed_id;
	private String workModifyTitle;
	private String workModifyContent;
	private Date workModifyStart;
	private Date workModifyEnd;
	private String workModifyTitleST;
	private Date workModifyEndST;
	private int myModiWorkProcessType;
	private int myModiWorkPriorityType;
	private int myModiWorkProgressType;
	private int myModiWorkSTProcessType;
	private int myModiWorkSTPriorityType;
	private List<Integer> modifyMembers;
	private List<Integer> subModifyMembers;
	private int sub_task_id;
	
	public UpdateFeedWorkDTO() {
		
	}

	@Builder
	public UpdateFeedWorkDTO(int feed_id, String workModifyTitle,
							 String workModifyContent, Date workModifyStart,
							 Date workModifyEnd, String workModifyTitleST,
							 Date workModifyEndST, int myModiWorkProcessType,
							 int myModiWorkPriorityType, int myModiWorkProgressType,
							 int myModiWorkSTProcessType, int myModiWorkSTPriorityType,
							 List<Integer> modifyMembers , List<Integer> subModifyMembers,
							 int sub_task_id) {
		this.feed_id = feed_id;
		this.workModifyTitle = workModifyTitle;
		this.workModifyContent = workModifyContent;
		this.workModifyStart = workModifyStart;
		this.workModifyEnd = workModifyEnd;
		this.workModifyTitleST = workModifyTitleST;
		this.workModifyEndST = workModifyEndST;
		this.myModiWorkProcessType = myModiWorkProcessType;
		this.myModiWorkPriorityType = myModiWorkPriorityType;
		this.myModiWorkProgressType = myModiWorkProgressType;
		this.myModiWorkSTProcessType = myModiWorkSTProcessType;
		this.myModiWorkSTPriorityType = myModiWorkSTPriorityType;
		this.modifyMembers = modifyMembers;
		this.subModifyMembers = subModifyMembers;
		this.sub_task_id = sub_task_id;
	}
	
}
