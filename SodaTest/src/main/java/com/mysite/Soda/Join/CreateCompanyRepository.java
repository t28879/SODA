package com.mysite.Soda.Join;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.Soda.DTO.JoinMember;

public interface CreateCompanyRepository extends JpaRepository<JoinMember, Integer>{
	JoinMember findByEmail(String email);
}
