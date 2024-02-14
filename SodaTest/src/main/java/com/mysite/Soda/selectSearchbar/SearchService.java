package com.mysite.Soda.selectSearchbar;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchService {
	
	private final SearchDAO searchdao;
	
	public List<SearchVO> searchProjects(String searchInput, int userNum) {
        return searchdao.searchProjects(searchInput, userNum);
    }

    public List<SearchVO> searchPosts(String searchInput, int userNum) {
        return searchdao.searchPosts(searchInput, userNum);
    }

    public List<SearchVO> searchFiles(String searchInput, int userNum) {
        return searchdao.searchFiles(searchInput, userNum);
    }
	
}
