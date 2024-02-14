package com.mysite.Soda.AllWork;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FinalWorkFeedWorkVO {
	
	public FinalWorkFeedWorkVO() {
	}
	private int project_id;
	private int feed_id;
	private int process;
	private String processimg;
	private String priorityimg;
	private String progressgraphcolor;
	private int priority;
	private String start_date;
	private String deadline;
	private int progress;
	private String title;
	private int sub_task_id;
	private int workmanager;
	private String workmanagername;
	private String content;
	private int writer;
	private String regist_date;
	private List<FinalWorkSubWorkVO> subworklist;
	private List<FeedWorkManagerVO> fwmanagerlist;
}
