package com.mysite.Soda.project;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectinfoVO {
	public ProjectinfoVO() {
	}
	private int project_id;
	private String name;
	private LocalDateTime project_date;
	private String projectmanager;
	private int allfeed;
	private int allreply;
	private int allmember;
	private int allchatting;
	private int allschedule;
	private int allwork;
	
}
