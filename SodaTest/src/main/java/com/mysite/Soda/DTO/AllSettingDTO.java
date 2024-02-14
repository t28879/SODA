package com.mysite.Soda.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="ALL_SETTING")
public class AllSettingDTO {
	
	@Id
	@Column(name="MEMBER_ID")
	private Integer memberId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MEMBER_ID", referencedColumnName = "MEMBER_ID", insertable = false, updatable = false)
	private JoinMember joinMember;
}
