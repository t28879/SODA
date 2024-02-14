package com.mysite.Soda.Join;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.Soda.DTO.JoinMember;

public interface JoinRepository extends JpaRepository<JoinMember, Integer>{
	JoinMember findByEmail(String email);
	Optional<JoinMember> findById(Integer userNum);
}

