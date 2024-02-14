package com.mysite.Soda.selectSearchbar;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchVO {
	
	// 공통으로 사용될 변수
	private String memberName;
	private Date regist_date;
	private String projectName;
	
	// 프로젝트 검색에 필요한 변수
	private String type; // 프로젝트 컬러
	
	// 글 검색에 필요한 변수
	private String title;
	private String content;
	private int feed_type;
	
	// 파일 검색에 필요한 변수
	private String fileNname;
	private String quantity;
	
	private int project_id;
	
}
