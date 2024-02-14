package com.mysite.Soda.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="MEMBER")
public class JoinMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ")
	@SequenceGenerator(name = "MEMBER_SEQ", sequenceName = "MEMBER_SEQ", allocationSize = 1)
	private Integer memberId;
	
	@NotBlank(message = "이름을 입력해주세요.")
	private String name;
	@NotBlank(message = "이메일을 입력해주세요.")
	private String email;
	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String pw;
	
	@CreationTimestamp
	@Column(name="JOIN_DATE")
	private LocalDateTime joinDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="COMPANY_ID")
	private CompanyDTO companyId;

	@OneToOne(mappedBy = "joinMember", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private AllSettingDTO allSetting;
	
	@OneToMany(mappedBy = "joinMember", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<WidgetDTO> widgets;
	
	public JoinMember() {
		this.widgets = new ArrayList<>();
	}
	
}
