package com.mysite.Soda.Join;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.Soda.DTO.CompanyDTO;

public interface FindCompanyRepository extends JpaRepository<CompanyDTO, Integer>{
	CompanyDTO findByName(String name);
	Optional<CompanyDTO> findById(Integer userNum);
}
