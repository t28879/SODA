package com.mysite.Soda.insertProFeedFolder;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsertFolderService {
	
	private final InsertFolderDAO insertfolderdao;
	
	public void proFeedFoler(String folderName, int project_id) {
        InsertFolderDTO folderDTO = insertfolderdao.selectfolder(folderName, project_id);
        int folder_id = folderDTO.getFolder_id();
        proFeedFolderInsert(folder_id, project_id);
    }

    public void proFeedFolderInsert(int folder_id, int project_id) {
        insertfolderdao.insertfolder(folder_id, project_id);
    }
    
    
}
