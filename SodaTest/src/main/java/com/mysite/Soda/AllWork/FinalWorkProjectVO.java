package com.mysite.Soda.AllWork;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FinalWorkProjectVO {
	
	public FinalWorkProjectVO() {
	}
	private String project_name;
	private List<FinalWorkFeedWorkVO> feedworklist;
}
