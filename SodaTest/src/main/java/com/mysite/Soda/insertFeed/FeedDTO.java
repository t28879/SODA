package com.mysite.Soda.insertFeed;

import java.sql.Date;
import java.util.List;

import groovy.transform.builder.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedDTO {
	// 공통 값
	private int type;
	private String titleInput;
	private String contentInput;
	private Date start_date;
	private Date deadline;
	
	// 피드 업무
	private int process;
	private int priority;
	private int progress;
	private int subProcess;
	private String subtaskInput;
	private Date subdeadline;
	private int subPriority;
	private List<Integer> selectedMembers;
	private List<Integer> selectSubMembers;
	
	// 피드 할일
	private String todoInput;
	
	// 임시 값
	private int project_id;
	
	// 하위업무 진척도
	private int subProgress;
	// 하위업무 시작일
	private Date subStart_date;
	
	// 일정의 참 불참 미정
	private int go;
	private int no;
	private int thinking;
	
	// 할일의 진척도 
	private int finish;
	
	public FeedDTO() {
		
	}
	
	@Builder
	public FeedDTO(int type, String titleInput, 
			String contentInput, Date start_date, 
			Date deadline, int process,
			int priority, int progress,
			int subProcess, String subtaskInput,
			Date subdeadline, int subPriority,
			String todoInput, int project_id,
			int manager_id, String manager,
			int subProgress,
			Date subStart_date, int go,
			int no, int thinking,
			int finish, List<Integer> selectedMembers,
			List<Integer> selectSubMembers) {
        this.type = type;
        this.titleInput = titleInput;
        this.contentInput = contentInput;
        this.start_date = start_date;
        this.deadline = deadline;
        this.process = process;
        this.priority = priority;
        this.progress = progress;
        this.subProcess = subProcess;
        this.subtaskInput = subtaskInput;
        this.subdeadline = subdeadline;
        this.subPriority = subPriority;
        this.todoInput = todoInput;
        this.project_id = project_id;
        this.subProgress = subProgress;
        this.subStart_date = subStart_date;
        this.go = go;
        this.no = no;
        this.thinking = thinking;
        this.finish = finish;
        this.selectedMembers = selectedMembers;
        this.selectSubMembers = selectSubMembers;
    }
}
