package com.mysite.Soda.selectFeed;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AllFeedSelectService {
	
	private final AllFeedSelectDAO allfeedselectdao;
	
	public List<AllFeedSelectVO> ProjectID(int project_id) {
		return allfeedselectdao.selectfeed(project_id);
	}
}
