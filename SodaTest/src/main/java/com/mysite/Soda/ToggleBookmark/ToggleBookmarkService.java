package com.mysite.Soda.ToggleBookmark;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToggleBookmarkService {
	
	private final ToggleBookmarkDAO Togglebookmarkdao;
	
	public void togglebookmark(int feed_id, int userNum) {
		if (Togglebookmarkdao.isBookmark(feed_id, userNum)) {
			Togglebookmarkdao.deleteBookmark(feed_id, userNum);
		} else {
			Togglebookmarkdao.insertBookmark(feed_id, userNum);
		}
	}
}
