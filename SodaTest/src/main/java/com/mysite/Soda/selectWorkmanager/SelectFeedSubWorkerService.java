package com.mysite.Soda.selectWorkmanager;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SelectFeedSubWorkerService {
	
	private final SelectFeedSubWorkerDAO selectfeedsubworkerdao;
	
	public List<SelectFeedSubWorkerVO> subworker(int sub_task_id) {
		return selectfeedsubworkerdao.subworker(sub_task_id);
	}
}
