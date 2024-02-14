package com.mysite.Soda.updateFeed;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateTodoCheckService {
	
	private final UpdateTodoCheckDAO updatetodocheckdao;
	
	public int updatecheck (int feed_id, int isChecked) {
		updatetodocheckdao.updatecheck(feed_id, isChecked);
		return updatetodocheckdao.checkbox(feed_id);
	}
}
