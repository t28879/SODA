package com.mysite.Soda.projectfeed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectColorVO {
	private String name; //프로젝트 이름
	private String type; // 프로젝트의 색상
	private int project_id;
}
