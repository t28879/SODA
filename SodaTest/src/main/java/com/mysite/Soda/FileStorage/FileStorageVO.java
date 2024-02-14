package com.mysite.Soda.FileStorage;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileStorageVO {
	private String filename;
	private String quantity;
	private String name;
	private String email;
	private LocalDateTime regist_date;
}
