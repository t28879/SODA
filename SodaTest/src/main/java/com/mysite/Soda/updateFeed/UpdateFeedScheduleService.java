package com.mysite.Soda.updateFeed;

import java.sql.Date;

import org.springframework.stereotype.Service;

import com.mysite.Soda.selectFeed.FeedScheduleVO;
import com.mysite.Soda.table.SelectAllFeedTableDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateFeedScheduleService {
	
	private final UpdateFeedScheduleDAO updatefeedscheduledao;
	
	private final SelectAllFeedTableDAO selectallfeedtabledao;
	
	public void updateschedule(UpdateFeedScheduleDTO updatefeedscheduledto, int userNum) {
		int feed_id = updatefeedscheduledto.getFeed_id();
		FeedScheduleVO allFeedSchedule = selectallfeedtabledao.selectAllFeedSchedule(feed_id);
		
		String title = updatefeedscheduledto.getScheduleModifyTitle();
		String content = updatefeedscheduledto.getScheduleModifyContent();
		Date start = updatefeedscheduledto.getScheduleModifyStart();
		Date end = updatefeedscheduledto.getScheduleModifyEnd();
		
		
		if(!title.isEmpty()) {
			String assistanceTitle = allFeedSchedule.getTitle();
			String newTitle = "제목이 " +  "\"" + assistanceTitle + "\" " +" 에서 → " + "\"" + title + "\" " +"(으)로 변경되었습니다.";
			updatefeedscheduledao.modifyTitle(title, feed_id);
			updatefeedscheduledao.insertReplyLog(newTitle, feed_id, userNum);
			updatefeedscheduledao.replyPlus(feed_id);
		}
		if(start != null) {
			Date assistanceStart = allFeedSchedule.getStart_date();
			String newStart = "시작일이 " +  "\"" + assistanceStart + "\" " +" 에서 → " + "\"" + start + "\" " +"(으)로 변경되었습니다.";
			updatefeedscheduledao.modifyStart(start, feed_id);
			updatefeedscheduledao.insertReplyLog(newStart, feed_id, userNum);
			updatefeedscheduledao.replyPlus(feed_id);
		}
		if(end != null) {
			Date assistanceDeadline = allFeedSchedule.getDeadline();
			String newDeadline = "마감일이 " +  "\"" + assistanceDeadline + "\" " +" 에서 → " + "\"" + end + "\" " +"(으)로 변경되었습니다.";
			updatefeedscheduledao.modifyEnd(end, feed_id);
			updatefeedscheduledao.insertReplyLog(newDeadline, feed_id, userNum);
			updatefeedscheduledao.replyPlus(feed_id);
		}
		if(!content.isEmpty()) {
			String assistanceContent = allFeedSchedule.getContent();
			String newContent = "내용이 " +  "\"" + assistanceContent + "\" " +" 에서 → " + "\"" + content + "\" " +"(으)로 변경되었습니다.";
			updatefeedscheduledao.modifyContent(content, feed_id);
			updatefeedscheduledao.insertReplyLog(newContent, feed_id, userNum);
			updatefeedscheduledao.replyPlus(feed_id);
		}
		
	}
}
