package com.mysite.Soda.MyProject;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MyProjectFolderVO {
	
	private int folder_id;
	private String name;
	private LocalDateTime recent_date;
}
