package com.mysite.Soda.selectWorkmanager;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SelectSubWorkmanagerService {
	
	private final SelectSubWorkmanagerDAO selectsubworkmanagerdao;
	
	public List<SelectSubWorkmanagerVO> projectID(int project_id){
		return selectsubworkmanagerdao.project_id(project_id);
	}
}
