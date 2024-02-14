package com.mysite.Soda.myCompanyMem;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyCompanyMemDAO {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	RowMapper<MyCompanyMemVO> rowmapper = (rs, rowNum) ->
		new MyCompanyMemVO(
			rs.getInt("member_id"),
			rs.getString("profile_image"),	
			rs.getString("name"),
			rs.getString("comName")	
			);
	
		public List<MyCompanyMemVO> getMyCompanyMem(int member_id) {
			String sql = "select m.member_id, m.profile_image, m.name, c.name as comName from member m, company c where c.company_id = m.company_id and m.member_id <> :member_id and c.company_id = (select company_id from member where member_id = :member_id)";

			Map<String,Object> param = Map.of("member_id", member_id);
			return jdbcTemplate.query(sql, param, rowmapper);	
		}
}
