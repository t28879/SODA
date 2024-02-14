package com.mysite.Soda.selectWorkmanager;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SelectWorkmanagerService {
	
	private final SelectWorkmanagerDAO selectworkmanagerdao;
	
	public List<SelectWorkmanagerVO> projectID(int project_id){
		return selectworkmanagerdao.project_id(project_id);
	}
}
