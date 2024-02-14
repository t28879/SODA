package com.mysite.Soda.FileDownLoadLog;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileDownLoadLogVO {
	private LocalDateTime downloads_date;
	private String filename;
	private String quantity;
	private String name;
	private String email;
}
