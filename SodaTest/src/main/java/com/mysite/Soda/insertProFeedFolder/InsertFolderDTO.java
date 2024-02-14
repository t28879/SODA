package com.mysite.Soda.insertProFeedFolder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertFolderDTO {
    private int folder_id;
    private String folderName;
    private int project_id;

    public InsertFolderDTO() {
    }

    public InsertFolderDTO(int folder_id, String folderName, int project_id) {
        this.folder_id = folder_id;
        this.folderName = folderName;
        this.project_id = project_id;
    }
}
