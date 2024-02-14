package com.mysite.Soda.IP;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.Soda.MemberVO;
import com.mysite.Soda.SearchMemDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IPService {
	
	private final IPDAO ipdao;
	private final SearchMemDAO searchdao;
	
	public List<IPVO> getIPinfo(int member_id){
		return ipdao.getIPinfo(member_id);
	}
	public List<IPVO> getIPlogininfo(int member_id){
		return ipdao.getIPlogininfo(member_id);
	}
	public List<IPMemberVO> getIPmeminfo(int member_id){
		return ipdao.getIPmemberinfo(member_id);
	}
	public List<IPMemberVO> getIPloginmemberinfo(int member_id){
		return ipdao.getIPloginmemberinfo(member_id);
	}
	public IPafterVO updateIPaddress(int member_id,String ipaddress,String ipexplain) {
		if(ipdao.selectipaddress(member_id, ipaddress)) {
			IPafterVO nullresponse = new IPafterVO();
			return nullresponse;
		}
		else {
			String target1 = "파일 보안 설정";
			String target2 = "지정된 IP에서만 다운로드 허용 변경";
			String change = insertDots(ipaddress)+"추가";
			ipdao.AdminChangeLog(member_id, target1,target2," ",change);
			ipdao.updateIPaddress(member_id, ipaddress, ipexplain);
			return ipdao.getAfterupdate(member_id, ipaddress);
		}
	}
	public IPafterVO updateLoginIPaddress(int member_id,String ipaddress,String ipexplain) {
		if(ipdao.selectloginipaddress(member_id, ipaddress)) {
			IPafterVO nullresponse = new IPafterVO();
			return nullresponse;
		}
		else {
			String target1 = "로그인 보안 설정";
			String target2 = "로그인 IP 설정 변경";
			String change = insertDots(ipaddress)+"추가";
			ipdao.AdminChangeLog(member_id, target1,target2," ",change);
			ipdao.updateloginIPaddress(member_id, ipaddress, ipexplain);
			return ipdao.getAfterloginipupdate(member_id, ipaddress);
		}
	}
	public void updateIPonoff(int member_id,int type,int onoff) {
		String target1 = "파일 보안 설정";
		String target2 = "";
		String ONorOFF = "OFF → ON";
		if(type == 1) {
			target2 = "지정된 IP에서만 다운로드 허용 변경";
			if(onoff == 0) {
				ONorOFF = "ON → OFF";
			}
			ipdao.AdminChangeLog(member_id, target1,target2," ",ONorOFF);
			ipdao.updateDownloadIPonoff(member_id, onoff);
		}
		else if(type == 2) {
			target2 = "이미지·파일 권한 예외 유저 관리 변경";
			if(onoff == 0) {
				ONorOFF = "ON → OFF";
			}
			ipdao.AdminChangeLog(member_id, target1,target2," ",ONorOFF);
			ipdao.updateDownloadIPMemberonoff(member_id, onoff);
		}
	}
	
	
	public void updateLoginIPonoff(int member_id,int type,int onoff) {
		String target1 = "로그인 보안 설정";
		String target2 = "";
		String ONorOFF = "OFF → ON";
		if(type == 1) {
			target2 = "지정된 IP에서만 로그인 허용 변경";
			if(onoff == 0) {
				ONorOFF = "ON → OFF";
			}
			ipdao.AdminChangeLog(member_id, target1,target2," " ,ONorOFF);
			ipdao.updateLoginIPonoff(member_id, onoff);
		}
		else if(type == 2) {
			target2 = "지정된 IP 로그인 예외 유저 관리 변경";
			if(onoff == 0) {
				ONorOFF = "ON → OFF";
			}
			ipdao.AdminChangeLog(member_id, target1,target2," ",ONorOFF);
			ipdao.updateLoginIPMemberonoff(member_id, onoff);
		}
	}
	public List<MemberVO> getMemberInfo(int member_id){
		return ipdao.getMemberInfo(member_id);
	}
	public void updateipmem(int member_id,List<Long> ipmemlist) {
		String target1 = "파일 보안 설정";
		String target2 = "이미지·파일 권한 예외 유저 관리 변경";
		String object = "";
		String change = "사용자추가";
		for(Long i : ipmemlist) {
			object = searchdao.searchmeminfoLongtype(i).getName()+
					"("+searchdao.searchmeminfoLongtype(i).getEmail()
					+")";
			ipdao.AdminChangeLog(member_id, target1, target2, object, change);
			ipdao.updateIPmember(member_id, i);
		}
	}
	public void updateloginipmem(int member_id,List<Long> ipmemlist) {
		String target1 = "로그인 보안 설정";
		String target2 = "로그인 권한 예외 유저 관리 변경";
		String change = "사용자추가";
		String object = "";
		for(Long i : ipmemlist) {
			object = searchdao.searchmeminfoLongtype(i).getName()+
					"("+searchdao.searchmeminfoLongtype(i).getEmail()
					+")";
			ipdao.AdminChangeLog(member_id, target1, target2, object, change);
			ipdao.updateloginIPmember(member_id, i);
		}
	}
	public void deleteIP(int master_id,int company_id, String iptext,int type){
		String target1 = "파일 보안 설정";
		String target2 = "";
		String change = "";
		String object = "";
		if(type == 1) {
			target2 = "지정된 IP에서만 다운로드 허용 변경";
			change = iptext +"삭제";
			ipdao.AdminChangeLog(master_id, target1, target2, object, change);
			ipdao.deleteIPaddress(company_id, iptext);
		}
		else if(type == 2) {
			target2 = "이미지·파일 권한 예외 유저 관리 변경";
			change = "사용자삭제";
			object = searchdao.searchmeminfobyemail(iptext).getName()+
					"("+searchdao.searchmeminfobyemail(iptext).getEmail()
					+")";
			ipdao.AdminChangeLog(master_id, target1, target2, object, change);
			ipdao.deleteIPmember(company_id, iptext);
		}
	}
	public void deleteLoginIP(int master_id,int company_id, String iptext,int type){
		String target1 = "로그인 보안 설정";
		String target2 = "";
		String change = "";
		String object = "";
		if(type == 1) {
			target2 = "로그인 IP 설정 변경";
			change = iptext +"삭제";
			ipdao.AdminChangeLog(master_id, target1, target2, object, change);
			ipdao.deleteLoginIPaddress(company_id, iptext);
		}
		else if(type == 2) {
			target2 = "로그인 권한 예외 유저 관리 변경";
			change = "사용자삭제";
			object = searchdao.searchmeminfobyemail(iptext).getName()+
					"("+searchdao.searchmeminfobyemail(iptext).getEmail()
					+")";
			ipdao.AdminChangeLog(master_id, target1, target2, object, change);
			ipdao.deleteLoginIPmember(company_id, iptext);
		}
	}
	private static String insertDots(String original) {
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < original.length(); i++) {
            resultBuilder.append(original.charAt(i));
            if ((i + 1) % 3 == 0 && (i + 1) < original.length()) {
                resultBuilder.append('.');
            }
        }
        return resultBuilder.toString();
    }
}
