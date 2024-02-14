package com.mysite.Soda.companyMem;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CompanyMemDAO {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	RowMapper<CompanyMemVO> rowmapper = (rs, rowNum) ->
		new CompanyMemVO(
			rs.getString("profile_image"),	
			rs.getString("name")	
			);
	
		public List<CompanyMemVO> getCompanyMem(int member_id) {
			String sql = "select profile_image, name from member where company_id = (select company_id from member where member_id = :member_id)";
			Map<String,Object> param = Map.of("member_id", member_id);
			return jdbcTemplate.query(sql, param, rowmapper);	
		}
}
