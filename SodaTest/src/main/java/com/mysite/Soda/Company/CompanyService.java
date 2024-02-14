package com.mysite.Soda.Company;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {
	
	private final CompanyDAO companydao;
	
	public void updateNewName(String newcmpName,int memberId) {
		String oldname = companydao.getCompanyinfo(memberId).getName();
		companydao.cmpnewName(newcmpName,memberId);
		companydao.cmpAdminUpdate(memberId, oldname, newcmpName);
	}
	public void updateNewUrl(String newcmpUrl,int memberId) {
		String oldurl = companydao.getCompanyinfo(memberId).getUrl();
		companydao.cmpnewUrl(newcmpUrl,memberId);
		companydao.cmpAdminurlUpdate(memberId, oldurl, newcmpUrl);
	}
	public CompanyVO getCompanyInfo(int member_id) {
		return companydao.getCompanyinfo(member_id);
	}
    
}
