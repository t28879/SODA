package com.mysite.Soda.Calendar;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IndividualVO {
	private LocalDateTime start;
	private String title;
	private int individualid;
}
