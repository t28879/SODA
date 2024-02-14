package com.mysite.Soda.updateFeed;

import org.springframework.stereotype.Service;

import com.mysite.Soda.selectFeed.FeedTextVO;
import com.mysite.Soda.table.SelectAllFeedTableDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateFeedTextService {
	
	private final UpdateFeedTextDAO updatefeedtextdao;
	
	private final SelectAllFeedTableDAO selectallfeedtabledao;
	
	public void updateText(String feedModifyTitle, String feedModifyContent, int feed_id, int userNum) {
		FeedTextVO allFeedText = selectallfeedtabledao.selectAllFeedText(feed_id);
		
		if(feedModifyTitle != null && feedModifyTitle != "") {
			String assistanceTitle = allFeedText.getTitle();
			String newTitle = "제목이 " +  "\"" + assistanceTitle + "\" " +" 에서 → " + "\"" + feedModifyTitle + "\" " +"로 변경되었습니다.";
			updatefeedtextdao.modifyTitle(feedModifyTitle, feed_id);
			updatefeedtextdao.insertReplyLog(newTitle, feed_id, userNum);
			updatefeedtextdao.replyplus(feed_id);
		}
		
		if(feedModifyContent != null && feedModifyContent != "") {
			String assistanceContent = allFeedText.getContent();
			String newContent = "내용이 " +  "\"" + assistanceContent + "\" " +" 에서 → " + "\"" + feedModifyContent + "\" " +"로 변경되었습니다.";
			updatefeedtextdao.modifyContent(feedModifyContent, feed_id);
			updatefeedtextdao.insertReplyLog(newContent, feed_id, userNum);
			updatefeedtextdao.replyplus(feed_id);
		}
	}
}
