package com.mysite.Soda.insertInvite;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class InsertInviteDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	public int insertInvite(int userNum, int project_id) {
		String sql = "INSERT INTO project_member(project_id, member_id, admin) VALUES(:project_id, :userNum, 0)";
		params.addValue("userNum", userNum);
		params.addValue("project_id", project_id);
		return jdbcTemplate.update(sql, params);
	}
}
