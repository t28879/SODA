package com.mysite.Soda.Answer;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {

	private final AnswerDAO answerdao;

	public void createanswer(int question_id, String content, int member_id) {
		answerdao.createanswer(question_id, content, member_id);
	}

	public void modifyanswer(int answer_id, String content) {
		answerdao.modifyanswer(answer_id, content);
	}
	public int getquestionid(int answer_id) {
		return answerdao.getquestionID(answer_id);
	}
	public void deleteanswer(int answer_id) {
		answerdao.deleteanswer(answer_id);
	}

}
