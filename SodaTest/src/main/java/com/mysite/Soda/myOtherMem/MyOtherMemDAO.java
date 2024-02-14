package com.mysite.Soda.myOtherMem;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyOtherMemDAO {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	RowMapper<MyOtherMemVO> rowmapper = (rs, rowNum) ->
		new MyOtherMemVO(
			rs.getString("profile_image"),	
			rs.getString("name"),
			rs.getString("comName")	
			);
	
		public List<MyOtherMemVO> getMyOtherMem(int member_id) {
			String sql = "select distinct m.profile_image, m.name, c.name as comName from member m, company c, project_member pm where "
					+ "m.member_id = pm.member_id and m.company_id = c.company_id and m.company_id != (select company_id from member where member_id = :member_id) and m.member_id <> :member_id and pm.project_id IN (select project_id from project_member where member_id = :member_id)";

			Map<String,Object> param = Map.of("member_id", member_id);
			return jdbcTemplate.query(sql, param, rowmapper);	
		}
}
