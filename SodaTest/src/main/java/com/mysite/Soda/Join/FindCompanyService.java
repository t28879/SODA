package com.mysite.Soda.Join;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.Soda.DTO.CompanyDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FindCompanyService {
	
	private final FindCompanyRepository findCompanyRepository;
	
	public Integer getCompanyId(String name) {
		CompanyDTO companyId = findCompanyRepository.findByName(name);
		if(companyId != null) {
			return companyId.getCompanyId();
		} else {
			return null;
		}
	}
	
	public Integer getComId(Integer userNum) {
		Optional<CompanyDTO> companyId = findCompanyRepository.findById(userNum);
		if(companyId != null) {
			CompanyDTO comId = companyId.get();
			return comId.getCompanyId();
		} else {
			return null;
		}
	}
	
	public boolean findCompany(String name) {
		CompanyDTO company = findCompanyRepository.findByName(name);
		if(company != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String findComName(String name) {
		CompanyDTO companyName = findCompanyRepository.findByName(name);
		if(companyName != null) {
			return String.valueOf(companyName.getName());
		} else {
			return null;
		}
	}
	
	
}
