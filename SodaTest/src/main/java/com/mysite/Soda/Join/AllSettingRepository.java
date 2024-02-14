package com.mysite.Soda.Join;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.Soda.DTO.AllSettingDTO;

public interface AllSettingRepository extends JpaRepository<AllSettingDTO, Integer>{
	Optional<AllSettingDTO> findById(Integer memberId);
}
