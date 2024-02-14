package com.mysite.Soda.Join;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.Soda.DTO.JoinMember;
import com.mysite.Soda.DTO.WidgetDTO;

public interface WidgetRepository extends JpaRepository<WidgetDTO, Integer>{
	List<WidgetDTO> findByJoinMember(JoinMember joinMember);
}
