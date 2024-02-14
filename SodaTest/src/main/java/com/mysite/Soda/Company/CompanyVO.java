package com.mysite.Soda.Company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompanyVO {
	private int company_id;
	private String name;
	private String logo;
	private String url;
	private int wm;
	private String ip;
	private int ipformem;
	private int loginip;
	private int logipformem;
	
}
