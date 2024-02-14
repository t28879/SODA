package com.mysite.Soda.Login;

import org.springframework.stereotype.Service;

import com.mysite.Soda.DTO.LoginMember;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindEmailService {
	
	private final FindEmailRepository findEmailRepository;
	
	public String getEmail(String email) {
		LoginMember memberEmail = findEmailRepository.findByEmail(email);
		if(memberEmail != null) {
			return memberEmail.getEmail();
		} else {
			return null;
		}
	}
}
