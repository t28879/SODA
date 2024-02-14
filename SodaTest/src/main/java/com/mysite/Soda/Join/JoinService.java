package com.mysite.Soda.Join;

import org.springframework.stereotype.Service;

import com.mysite.Soda.DTO.AllSettingDTO;
import com.mysite.Soda.DTO.CompanyDTO;
import com.mysite.Soda.DTO.JoinMember;
import com.mysite.Soda.DTO.WidgetDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinService {

	private final AllSettingRepository allSettingRepository;
	private final JoinRepository joinRepository;
	private final WidgetRepository widgetRepository;
	
	public String memberEmail(String email) {
		JoinMember memberEmail = joinRepository.findByEmail(email);
		if(memberEmail != null) {
			return String.valueOf(memberEmail.getEmail());
		} else {
			return null;
		}
	}
	
	public Integer getMemberId(String email) {
		JoinMember memberId = joinRepository.findByEmail(email);
		if(memberId != null) {
			return memberId.getMemberId();
		} else {
			return null;
		}
	}
	
	public boolean joinMember(String email, String name, String pw) {
		JoinMember member = joinRepository.findByEmail(email);
		
		if(member == null) {
			member = new JoinMember();
			member.setEmail(email);
			member.setName(name);
			member.setPw(pw);
			JoinMember saveMember = joinRepository.save(member);
			Integer memberId = saveMember.getMemberId();
			if(memberId != null) {
				AllSettingDTO allSetting = new AllSettingDTO();
				allSetting.setMemberId(memberId);
				allSettingRepository.save(allSetting);
				
				createWidgetsForNewMember(email);
			}
			
			return true;
		} else 
			return false;
	}
	
	public boolean enterMember(CompanyDTO companyId, String email, String name, String pw) {
		JoinMember member = joinRepository.findByEmail(email);
		
		if(member == null) {
			member = new JoinMember();
			member.setEmail(email);
			member.setName(name);
			member.setPw(pw);
			member.setCompanyId(companyId);
			JoinMember saveMember = joinRepository.save(member);
			Integer memberId = saveMember.getMemberId();
			if(memberId != null) {
				AllSettingDTO allSetting = new AllSettingDTO();
				allSetting.setMemberId(memberId);
				allSettingRepository.save(allSetting);
				
				createWidgetsForNewMember(email);
			}
			return true;
		} else {
			return false;
		}
	}
	
	public void createWidgetsForNewMember(String email) {
        JoinMember member = joinRepository.findByEmail(email);
        if (member != null) {

            // WidgetDTO 생성 및 저장
            for (int i = 1; i <= 6; i++) {
                WidgetDTO widget = new WidgetDTO();
                widget.setJoinMember(member);
                widget.setWidgetName("list-group-item" + i);
                widget.setOrderColumn(i - 1);
                widgetRepository.save(widget);
            }
        }
    }

	
	
}
