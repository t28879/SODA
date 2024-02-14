package com.mysite.Soda.Login;

import org.springframework.stereotype.Service;

import com.mysite.Soda.DTO.LoginMember;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChangePwService {
	
	private final ChangePwRepository changePwRepository;
	
	public boolean changePw(String email, String pw) {
		LoginMember member = changePwRepository.findByEmail(email);
		if(member != null) {
			member.setPw(pw);
			changePwRepository.save(member);
			return true;
		} else {
			return false;
		}
	}
}
