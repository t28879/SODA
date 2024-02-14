package com.mysite.Soda.Login;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.Soda.DTO.LoginMember;


public interface LoginRepository extends JpaRepository<LoginMember, Integer>{
	LoginMember findByEmailAndPw(String email, String pw);
	LoginMember findByEmail(String email);
}
