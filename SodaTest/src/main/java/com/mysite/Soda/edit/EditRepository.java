package com.mysite.Soda.edit;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EditRepository extends JpaRepository<EditMember, Integer>{
	Optional<EditMember> findById(Integer userNum);
	EditMember findByPw(String pw);
}
