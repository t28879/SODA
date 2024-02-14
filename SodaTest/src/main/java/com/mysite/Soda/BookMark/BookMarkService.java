package com.mysite.Soda.BookMark;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookMarkService {
	
	private final BookMarkDAO bookmarkdao;
	
	public List<FeedTypeVO> getbookmark(int member_id){
		List<FeedTypeVO> masterbooklist = new ArrayList<FeedTypeVO>();
		List<BookMarkVO> booklist = bookmarkdao.getbookmark(member_id);
		for(BookMarkVO bvo : booklist) {
			FeedTypeVO mbm = new FeedTypeVO();
			switch(bvo.getType()) {
			case 1:
				mbm = bookmarkdao.getfeedtextinfo(bvo.getFeed_id());
				mbm.setType(1);
				break;
			case 2:
				mbm = bookmarkdao.getfeedworkinfo(bvo.getFeed_id());
				mbm.setType(2);
				break;
			case 3:
				mbm = bookmarkdao.getfeedscheduleinfo(bvo.getFeed_id());
				mbm.setType(3);
				break;
			case 4:
				mbm = bookmarkdao.getfeedtodoinfo(bvo.getFeed_id());
				mbm.setType(4);
				break;
			default:break;
			}
			masterbooklist.add(mbm);
		}
		return masterbooklist;
	}
    
}
