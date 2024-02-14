package com.mysite.Soda.AllLikey;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToggleLikeyService {
	
	private final ToggleLikeyDAO togglelikeydao;
	
	public void togglelikey(int feed_id, int userNum) {
        if (togglelikeydao.isLiked(feed_id, userNum)) {
        	togglelikeydao.deleteLike(feed_id, userNum);
        	togglelikeydao.updatereplyminus(feed_id);
        } else {
        	togglelikeydao.insertLike(feed_id, userNum);
        	togglelikeydao.updatereplyplus(feed_id);
        }
    }
	
}
