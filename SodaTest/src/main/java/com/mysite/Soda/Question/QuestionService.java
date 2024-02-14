package com.mysite.Soda.Question;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.Soda.DataNotFoundException;
import com.mysite.Soda.Answer.AnswerDAO;
import com.mysite.Soda.Answer.AnswerVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
	
	private final QuestionDAO questiondao;
	private final AnswerDAO answerdao;
	public List<QuestionVO> getquestionlist(int member_id,int pagenum) {
		if(questiondao.checkquestionlist(member_id)) {
			List<QuestionVO> qfv = new ArrayList<QuestionVO>();
			List<QuestionVO> qv = questiondao.getquestionlist(member_id,pagenum);
			for(QuestionVO q : qv) {
				q.setAnswerList(answerdao.getanswerlist(q.getQuestion_id()));
				qfv.add(q);
			}
			return qfv;
		}
		else {
            throw new DataNotFoundException("question not found");
        }
	}
	public int getquestionlistsize(int member_id) {
		int size = questiondao.getquestionlistsize(member_id);
		if(size%20 == 0) {
			return size/20;
		}
		return size/20+1;
	}
	public QuestionDetailVO getquestiondetail(int questionID) {
		QuestionDetailVO qdv = new QuestionDetailVO();
		QuestionVO qv = questiondao.getquestion(questionID);
		List<AnswerVO> av = answerdao.getanswerlist(questionID);
		qdv.setQuestion_id(qv.getQuestion_id());
		qdv.setSubject(qv.getSubject());
		qdv.setContent(qv.getContent());
		qdv.setCreatedate(qv.getCreatedate());
		qdv.setAnswerList(av);
		qdv.setName(qv.getName());
		qdv.setEmail(qv.getEmail());
		qdv.setMember_id(qv.getMember_id());
		qdv.setMODIFICATION_DATE(qv.getMODIFICATION_DATE());
		return qdv;
	}
	public void createquestion(String subject,String content,int member_id) {
		questiondao.createquestion(subject, content, member_id);
	}
	public void modifyquestion(String content,int question_id) {
		questiondao.modifyquestion(content, question_id);
	}
	public void deletequestion(int question_id) {
		questiondao.deletequestion(question_id);
	}
}
