package com.mysite.Soda.updateFeed;

import java.sql.Date;

import org.springframework.stereotype.Service;

import com.mysite.Soda.selectFeed.FeedTodoVO;
import com.mysite.Soda.table.SelectAllFeedTableDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateFeedTodoService {
	
	private final UpdateFeedTodoDAO updatefeedtododao;
	
	private final SelectAllFeedTableDAO selectallfeedtabledao;
	
	public void updatetodo(UpdateFeedTodoDTO updatefeedtododto, int userNum) {
		int feed_id = updatefeedtododto.getFeed_id();
		FeedTodoVO allFeedTodo = selectallfeedtabledao.selectAllFeedTodo(feed_id);
		
		String title = updatefeedtododto.getTitle();
		Date end = updatefeedtododto.getEnd();
		String content = updatefeedtododto.getContent();

		if(!title.isEmpty()) {
			String assistanceTitle = allFeedTodo.getTitle();
			String newTitle = "제목이 " +  "\"" + assistanceTitle + "\" " +" 에서 → " + "\"" + title + "\" " +"(으)로 변경되었습니다.";
			updatefeedtododao.modifyTitle(feed_id, title);
			updatefeedtododao.insertReplyLog(newTitle, feed_id, userNum);
			updatefeedtododao.replyPlus(feed_id);
		}
		if(end != null) {
			Date assistanceDeadline = allFeedTodo.getDeadline();
			String newDeadline = "마감일이 " +  "\"" + assistanceDeadline + "\" " +" 에서 → " + "\"" + end + "\" " +"(으)로 변경되었습니다.";
			updatefeedtododao.modifyEnd(feed_id, end);
			updatefeedtododao.insertReplyLog(newDeadline, feed_id, userNum);
			updatefeedtododao.replyPlus(feed_id);
		}
		if(!content.isEmpty()) {
			String assistanceContent = allFeedTodo.getTitle();
			String newContent = "내용이 " +  "\"" + assistanceContent + "\" " +" 에서 → " + "\"" + content + "\" " +"(으)로 변경되었습니다.";
			updatefeedtododao.modifyEnd(feed_id, content);
			updatefeedtododao.insertReplyLog(newContent, feed_id, userNum);
			updatefeedtododao.replyPlus(feed_id);
		}
	}
}
