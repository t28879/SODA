package com.mysite.Soda.project;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectMemberVO {
	private int member_id;
	private String name;
	private String email;
	private String deptname;
}
