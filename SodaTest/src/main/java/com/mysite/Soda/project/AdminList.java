package com.mysite.Soda.project;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminList {
	private int project_id;
	private String projectname;
	 private List<Long> adList;
}
