package com.mysite.Soda.Question;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.Soda.MainService;
import com.mysite.Soda.Answer.AnswerForm;
import com.mysite.Soda.color.ColorVO;
import com.mysite.Soda.folder.FolderVO;
import com.mysite.Soda.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QuestionController {
	private final QuestionService questionservice;
	private final HttpSession session;
	private final MainService mainservice;
	
	
	@GetMapping("/questionlist/{pagenum}")
	public String list(Model model,@PathVariable("pagenum") int pagenum){
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		List<QuestionVO> questionList = questionservice.getquestionlist(member_id,pagenum);
		int totalPages = questionservice.getquestionlistsize(member_id);
		model.addAttribute("questionList",questionList );
		model.addAttribute("currentPage", pagenum);
		model.addAttribute("totalPages",totalPages);
		List<ColorVO> color = mainservice.getColor(member_id);
		model.addAttribute("projectWithColor", color);
		
		List<FolderVO> folder = mainservice.getFolder(member_id);
		model.addAttribute("folder", folder);
		MemberVO member = mainservice.getMember(member_id); 
		model.addAttribute("member",member);
		return "QuestionList";
	}
	
	@GetMapping(value = "/questionlist_detail/{question_id}")
    public String detail(Model model, @PathVariable("question_id") Integer id, AnswerForm answerForm) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		QuestionDetailVO question = questionservice.getquestiondetail(id);
		model.addAttribute("question",question);
		model.addAttribute("member_id",member_id);
        return "Question_Detail";
    }
	@GetMapping("/questioncreate")
    public String questionCreate(Model model,QuestionForm questionform) {
      Object memIdobj = session.getAttribute("memberId");
      int member_id = (int) memIdobj;
      List<ColorVO> color = mainservice.getColor(member_id);
      model.addAttribute("projectWithColor", color);
      
      List<FolderVO> folder = mainservice.getFolder(member_id);
      model.addAttribute("folder", folder);
      MemberVO member = mainservice.getMember(member_id); 
      model.addAttribute("member",member);
        return "QuestionForm";
    }
	@PostMapping("/questioncreate")
    public String questionCreate(@Valid QuestionForm questionform,BindingResult bindingResult) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		if(bindingResult.hasErrors()) {
			return "QuestionForm";
		}
		questionservice.createquestion(questionform.getSubject(), questionform.getContent(),member_id);	
        return "redirect:/questionlist/1"; // 질문 저장후 질문목록으로 이동
    }
	@PostMapping("/questionmodify")
    public ResponseEntity<Void> QuestionModify(
    		@RequestParam(name = "question_id")int question_id,
			@RequestParam(name = "content")String content) {
		questionservice.modifyquestion(content, question_id);
        return ResponseEntity.ok().build();
    }
	@GetMapping("/questiondelete/{question_id}")
    public String Questiondelete(
    		@PathVariable("question_id") int question_id) {
		questionservice.deletequestion(question_id);
        return "redirect:/questionlist/1";
    }
	
}
