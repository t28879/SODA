package com.mysite.Soda.Join;

import org.springframework.stereotype.Service;

import com.mysite.Soda.DTO.CompanyDTO;
import com.mysite.Soda.DTO.JoinMember;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateCompanyService {

	private final CreateCompanyRepository createCompanyRepository;
	private final CompanyRepository comRepository;
	
	public Integer getMemberId(String email) {
		JoinMember memberId = createCompanyRepository.findByEmail(email);
		if(memberId != null) {
			return memberId.getMemberId();
		} else {
			return null;
		}
	}
	
	public Integer createCompany(String email, String name, String url) {
		CompanyDTO comName = comRepository.findByName(name);
		if(comName == null) {
			Integer memberId = getMemberId(email);
			if(memberId != null) {
				CompanyDTO company = new CompanyDTO();
				company.setName(name);
				company.setUrl("https://www."+url+".soda.team");
				
				CompanyDTO saveCompany = comRepository.save(company);
				JoinMember member = createCompanyRepository.findById(memberId).orElse(null);
				if(member != null) {
					member.setCompanyId(saveCompany);
					createCompanyRepository.save(member);
					return memberId;
				}else {
					return null;
				}
			} else return null;
		} else {
			return null;
		}
	}
}
