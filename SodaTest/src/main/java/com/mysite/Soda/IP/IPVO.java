package com.mysite.Soda.IP;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IPVO {
	private int ip;
	private String ipaddress;
	private String text;
	private LocalDateTime up_date;
	private String email;
	private int company_id;
}
