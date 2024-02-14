package com.mysite.Soda.Question;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.mysite.Soda.Answer.AnswerVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionDetailVO {
	public QuestionDetailVO() {
	}
	private int question_id;
	private String subject;
	private String content;
	private LocalDateTime createdate;
	private Date MODIFICATION_DATE;
	private List<AnswerVO> answerList;
	private String name;
	private String email;
	private int member_id;
}
