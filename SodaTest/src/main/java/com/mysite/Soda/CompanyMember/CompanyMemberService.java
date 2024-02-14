package com.mysite.Soda.CompanyMember;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.Soda.SearchMemDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyMemberService {
	
	private final CompanyMemberDAO companymemberdao;
	private final SearchMemDAO searchmemdao;
	
	public List<CompanyMemberVO> getallcmpmem(int member_id){
		return companymemberdao.getallcmpmem(member_id);
	}
	public int modifymember(int master_id,String email,String name, String job_name,String phone_num) {
		String oldname = searchmemdao.searchmeminfobyemail(email).getName();
		String oldjobname = searchmemdao.searchmeminfobyemail(email).getJobname();
		String oldphonenum = searchmemdao.searchmeminfobyemail(email).getPhone_num();
		String full=name +"("+email+")";
		String change="";
		if(!oldname.equals(name)) {
			change = oldname +" -> "+name;
			companymemberdao.NameChangeLog(master_id, full, change);
		}
		if(!oldjobname.equals(job_name)) {
			change = oldjobname +" -> "+job_name;
			companymemberdao.JobChangeLog(master_id, full, change);
		}
		if(!oldphonenum.equals(phone_num)) {
			change = oldphonenum +" -> "+phone_num;
			companymemberdao.PhonenumChangeLog(master_id, full, change);
		}
		return companymemberdao.modifymember(email, name, job_name, phone_num);
	}
	public int modifydept(int master_id,String email,String deptname) {
		String olddeptname = searchmemdao.searchmeminfobyemail(email).getDeptname();
		String name = searchmemdao.searchmeminfobyemail(email).getName();
		String full=name +"("+email+")";
		String change="";
		if(!olddeptname.equals(deptname)) {
			change = olddeptname +" -> "+deptname;
			companymemberdao.NameChangeLog(master_id, full, change);
		}
		return companymemberdao.modifydept(email, deptname);
	}
	public int banorremember(int member_id,int Banor,int master_id) {
		String BanOrRe = "";
		String name = searchmemdao.searchmeminfo(member_id).getName();
		String email = searchmemdao.searchmeminfo(member_id).getEmail();
		String full = name +"("+email+")";
		if(Banor == 0) BanOrRe = "이용중지";
		else if(Banor == 1) BanOrRe = "이용재개";
		companymemberdao.AdminBanLog(master_id,full,BanOrRe);
		return companymemberdao.banorre(member_id, Banor);
	}
	public int modifymanager(int member_id,int YorN,int master_id) {
		String BanOrRe = "";
		String name = searchmemdao.searchmeminfo(member_id).getName();
		String email = searchmemdao.searchmeminfo(member_id).getEmail();
		String full = name +"("+email+")";
		if(YorN == 0) BanOrRe = "관리자 삭제";
		else if(YorN == 1) BanOrRe = "관리자 추가";
		companymemberdao.AdminmanagerChangeLog(master_id,full,BanOrRe);
		return companymemberdao.modifymanager(member_id, YorN);
	}
}
