package com.mysite.Soda.Login;

import org.springframework.stereotype.Service;

import com.mysite.Soda.DTO.LoginMember;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginService {

	private final LoginRepository loginRepository;
	
	public Integer getMemberId(String email, String pw) {
		LoginMember memberId = loginRepository.findByEmailAndPw(email, pw);
		if(memberId != null)
			return Integer.valueOf(memberId.getMemberId());
		else
			return null;
	}
	
	public Integer findMemberId(String email) {
		LoginMember memberId = loginRepository.findByEmail(email);
		if(memberId != null)
			return Integer.valueOf(memberId.getMemberId());
		else
			return null;
	}
}
