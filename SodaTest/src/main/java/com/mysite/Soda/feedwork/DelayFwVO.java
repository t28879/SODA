package com.mysite.Soda.feedwork;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DelayFwVO {
	private int process;
	private String title;
	private int priority;
	private String deadline;
}
