package com.mysite.Soda.myPeople;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyPeopleDAO {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	RowMapper<MyPeopleVO> rowmapper = (rs, rowNum) ->
		new MyPeopleVO(
			rs.getString("member_name"),	
			rs.getString("profile_image"),	
			rs.getString("comName")	
			);
	
		public List<MyPeopleVO> getMyPeople(int member_id) {
			String sql = "SELECT m.name as member_name, m.profile_image, c.name as comName FROM member m, company c "
			        + "WHERE m.company_id = c.company_id "
			        + "AND c.company_id IN (SELECT company_id FROM member WHERE member_id = :member_id) "
			        + "AND m.member_id <> :member_id "
			        + "UNION "
			        + "SELECT m.name as member_name, m.profile_image, c.name as comName FROM member m, project_member pm, company c "
			        + "WHERE m.member_id = pm.member_id "
			        + "AND pm.project_id IN (SELECT pm.project_id FROM project_member pm WHERE pm.member_id = :member_id) "
			        + "AND c.company_id = m.company_id "
			        + "AND m.member_id <> :member_id";

			Map<String,Object> param = Map.of("member_id", member_id);
			return jdbcTemplate.query(sql, param, rowmapper);	
		}
}
