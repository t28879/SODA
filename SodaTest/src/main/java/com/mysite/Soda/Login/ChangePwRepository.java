package com.mysite.Soda.Login;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.Soda.DTO.LoginMember;

public interface ChangePwRepository extends JpaRepository<LoginMember, Integer>{
	LoginMember findByEmail(String email);
	LoginMember findByPw(String pw);
}
