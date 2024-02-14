package com.mysite.Soda.projectfeed;

import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProjectColorDAO {
	
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	RowMapper<ProjectColorVO> rowMapper = (rs, rowNum) ->
		new ProjectColorVO(
				rs.getString("name"),
				rs.getString("type"),
				rs.getInt("project_id")
				);
		
	public ProjectColorVO projectID(int project_id) {
		String sql = "SELECT p.name, c.type, p.project_id FROM project p JOIN color c ON p.color_id = c.color_id WHERE p.project_id = :id";
		Map<String, Object> param = Map.of("id", project_id);
		return jdbcTemplate.queryForObject(sql, param, rowMapper);
	}
	
	
}
