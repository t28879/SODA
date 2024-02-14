package com.mysite.Soda.folder;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FolderService {
	private final FolderDAO folderdao;
	
	public List<FolderVO> getFolder(int member_id) {
		return folderdao.getFolder(member_id);
	}
}
