package com.mysite.Soda.project;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectAdminVO {
	private String name;
	private String email;
	private String deptname;
	private String projectname;
	private int project_id;
	private int member_id;
	
}
