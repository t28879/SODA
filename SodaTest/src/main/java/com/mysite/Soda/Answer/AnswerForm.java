package com.mysite.Soda.Answer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerForm {
	    @NotEmpty(message="내용은 필수항목입니다.")
	    private String content;
}
