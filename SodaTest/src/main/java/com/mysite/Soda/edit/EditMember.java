package com.mysite.Soda.edit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="MEMBER")
public class EditMember {

	@Id
	private Integer memberId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PHONE_NUM")
	private String phoneNum;
	
	@Column(name="PW")
	private String pw;
	
	@Column(name="JOB_NAME")
	private String jobName;
	
	@Column(name="PROFILE_IMAGE")
	private String profileImage;
	
}
