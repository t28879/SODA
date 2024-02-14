package com.mysite.Soda.Answer;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.Soda.MainService;
import com.mysite.Soda.Question.QuestionDetailVO;
import com.mysite.Soda.Question.QuestionService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@Controller
@RequiredArgsConstructor
public class AnswerController {
	private final AnswerService answerservice;
	private final QuestionService questionservice;
	private final MainService mainservice;
	private final HttpSession session;
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerform,BindingResult bindingResult){
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		QuestionDetailVO question = questionservice.getquestiondetail(id);
		if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "Question_Detail";
        }
		answerservice.createanswer(id, answerform.getContent(),member_id);
		return String.format("redirect:/questionlist_detail/%s", id);
	}
	@PostMapping("/modifyanswer")
	public ResponseEntity<Void> modifyAnswer(
			@RequestParam(name = "answer_id")int answer_id,
			@RequestParam(name = "content")String content){
		answerservice.modifyanswer(answer_id, content);
		return ResponseEntity.ok().build();
	}
	@GetMapping("/delete/{answer_id}")
	public String modifyAnswer(
			@PathVariable("answer_id") int answer_id){
		int question_id = answerservice.getquestionid(answer_id);
		answerservice.deleteanswer(answer_id);
		return String.format("redirect:/questionlist_detail/%s", question_id);
	}
}
