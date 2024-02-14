package com.mysite.Soda.Answer;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnswerVO {
	private int answer_id;
	private String content;
	private Date createdate;
	private int question_id;
	private int member_id;
	private String name;
	private String email;
	private Date MODIFICATION_DATE;
}
