package com.mysite.Soda.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="COMPANY")
public class CompanyDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPANY_SEQ")
	@SequenceGenerator(name = "COMPANY_SEQ", sequenceName = "COMPANY_SEQ", allocationSize = 1)
	@Column(name="COMPANY_ID")
	private Integer companyId;
	
	private String name;
	private String url;
	
}
