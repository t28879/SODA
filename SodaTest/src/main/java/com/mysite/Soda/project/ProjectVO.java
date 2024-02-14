package com.mysite.Soda.project;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectVO {
	private int project_id;
	private String name;
	private int authority;
	private LocalDateTime project_date;
	private int per_onoff;
	private int tag_id;
	private int calendar_id;
	private int color_id;
	private int company_id;
	private int register;
	private String url;
	private int pro_cnt;
	private String visible;
	private LocalDateTime recent_update;
}
