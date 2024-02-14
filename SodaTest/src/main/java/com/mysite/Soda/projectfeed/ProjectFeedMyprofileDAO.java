package com.mysite.Soda.projectfeed;

import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProjectFeedMyprofileDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	RowMapper<ProjectFeedMyprofileVO> rowMapper = (rs, rowNum) ->
		new ProjectFeedMyprofileVO (
				rs.getString("member_name"),
				rs.getString("profile_image"),
				rs.getString("company_name"),
				rs.getInt("member_id")
				);
		
	public ProjectFeedMyprofileVO memberID(int member_id) {
		String sql = "SELECT m.name AS member_name, m.profile_image, c.name AS company_name, m.member_id "
				+ "FROM member m JOIN company c ON m.company_id = c.company_id "
				+ "WHERE m.member_id = :id";
		Map<String, Object> param = Map.of("id", member_id);
		return jdbcTemplate.queryForObject(sql, param, rowMapper);
	}
}
