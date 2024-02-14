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
public class FinalWorkSubWorkVO {
	
	public FinalWorkSubWorkVO() {
	}
	private int feed_id;
	private int sub_task_id;
	private String name;
	private int sub_process;
	private String processimg;
	private String priorityimg;
	private String progressgraphcolor;
	private String deadline;
	private String start_date;
	private int progress;
	private int member_id;
	private int sub_priority;
	private String workmanagername;
	private List<FeedWorkManagerVO> managerlist;
}
