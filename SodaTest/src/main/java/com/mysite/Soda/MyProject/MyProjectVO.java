package com.mysite.Soda.MyProject;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MyProjectVO {
	
	public MyProjectVO() {
	}
	private int project_id;
	private String name;
	private String color;
}
