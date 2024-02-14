package com.mysite.Soda.MyPost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.Soda.BookMark.BookMarkDAO;
import com.mysite.Soda.BookMark.FeedTypeVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPostService {
	
	private final MyPostDAO mypostdao;
	private final BookMarkDAO bookmarkdao;
	
	public List<FeedTypeVO> getmyposts(int member_id){
		List<FeedTypeVO> masterbooklist = new ArrayList<FeedTypeVO>();
		List<MyPostVO> feedlist = mypostdao.getmypost(member_id);
		List<MyReplyVO> replylist = mypostdao.getreplylist(member_id);
		for(MyPostVO mpv : feedlist) {
			FeedTypeVO mbm = new FeedTypeVO();
			switch(mypostdao.getfeedtype(mpv.getFeed_id())) {
			case 1:
				mbm = bookmarkdao.getfeedtextinfo(mpv.getFeed_id());
				mbm.setType(1);
				break;
			case 2:
				mbm = bookmarkdao.getfeedworkinfo(mpv.getFeed_id());
				mbm.setType(2);
				break;
			case 3:
				mbm = bookmarkdao.getfeedscheduleinfo(mpv.getFeed_id());
				mbm.setType(3);
				break;
			case 4:
				mbm = bookmarkdao.getfeedtodoinfo(mpv.getFeed_id());
				mbm.setType(4);
				break;
			default:break;
			}
			mbm.setProject_id(mypostdao.getprojectid(mpv.getFeed_id()));         
			masterbooklist.add(mbm);
		}
		for(MyReplyVO mrv : replylist) {
			FeedTypeVO mbm = new FeedTypeVO();
			mbm.setTitle(mrv.getTitle());
			mbm.setProjectname(mypostdao.getfeedtitle(mrv.getFeedID()));
			mbm.setRegist_date(mrv.getReply_date());
			mbm.setType(5);
			mbm.setProject_id(mypostdao.getprojectid(mrv.getFeedID()));    
			masterbooklist.add(mbm);
		}
		return masterbooklist;
	}
}
