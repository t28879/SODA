package com.mysite.Soda.IP;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IPafterVO {
	public IPafterVO() {
	}
	private String ipaddress;
	private int company_id;
	private LocalDateTime up_date;
	private String email;
}
