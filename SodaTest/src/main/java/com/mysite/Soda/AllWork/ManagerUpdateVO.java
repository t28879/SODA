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
public class ManagerUpdateVO {
	
	public ManagerUpdateVO() {
	}
	private int type;
	private int feedorsubID;
	private List<Integer> managerIDlist;
	
}
