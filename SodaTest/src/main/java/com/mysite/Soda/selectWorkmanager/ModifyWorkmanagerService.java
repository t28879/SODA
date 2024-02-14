package com.mysite.Soda.selectWorkmanager;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModifyWorkmanagerService {
	
	private final ModifyWorkmanagerDAO modifyworkmanagerdao;
	
	public List<ModifyWorkmanagerVO> projectID(int project_id){
		return modifyworkmanagerdao.projectID(project_id);
	}
}
