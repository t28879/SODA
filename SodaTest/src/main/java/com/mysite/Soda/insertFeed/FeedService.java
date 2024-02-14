package com.mysite.Soda.insertFeed;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedService {
	
	private final FeedDAO feeddao;
	
	@Transactional
	public void feedInsert(FeedDTO feedDTO, int userNum) {
		int type = feedDTO.getType();
		
		switch (type) {
			case 1 :
				feeddao.feed(feedDTO.getProject_id(), type);
				feeddao.feedtext(feedDTO.getTitleInput(), feedDTO.getContentInput(),userNum);
				break;
			case 2 : 
				feeddao.feed(feedDTO.getProject_id(), type);
				feeddao.feedWork(feedDTO.getProcess(), feedDTO.getPriority(), 
						feedDTO.getStart_date(), feedDTO.getDeadline(), 
						feedDTO.getProgress(), feedDTO.getTitleInput(),
						feedDTO.getContentInput(), userNum);
				feeddao.subtask(feedDTO.getSubtaskInput(), feedDTO.getSubProcess(),
								feedDTO.getSubdeadline(), feedDTO.getSubProgress(),
								userNum, feedDTO.getSubPriority(),
								feedDTO.getSubStart_date());
				List<Integer> arr = feedDTO.getSelectedMembers();
				for(int i : arr) {
					feeddao.fwManager(i);
				}
				List<Integer> arr2 = feedDTO.getSelectSubMembers();
				for(int j : arr2) {
					feeddao.stfwmanager(j);
				}
				break;
			case 3 :
				feeddao.feed(feedDTO.getProject_id(), type);
				feeddao.feedSchedule(feedDTO.getStart_date(), feedDTO.getDeadline(),
									feedDTO.getTitleInput(), feedDTO.getContentInput(),
									userNum, feedDTO.getGo(),
									feedDTO.getNo(), feedDTO.getThinking());
				break;
			case 4 : 
				feeddao.feed(feedDTO.getProject_id(), type);
				feeddao.feedTodo(feedDTO.getTodoInput(),
								feedDTO.getFinish(), feedDTO.getDeadline(),
								feedDTO.getTitleInput(),
								userNum);
				break;
		}
	}
	
	
}
