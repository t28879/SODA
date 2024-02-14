package com.mysite.Soda.AdminChangeLog;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdminChangeLogVO {
	private String name;
	private String email;
	private String menu;
	private String object;
	private String function;
	private String changes;
	private LocalDateTime change_date;
}
