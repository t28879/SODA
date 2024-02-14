package com.mysite.Soda.Join;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.Soda.DTO.CompanyDTO;

public interface CompanyRepository extends JpaRepository<CompanyDTO, Integer>{
	CompanyDTO findByName(String name);
}
