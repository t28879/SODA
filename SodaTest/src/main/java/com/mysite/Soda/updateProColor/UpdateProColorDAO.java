package com.mysite.Soda.updateProColor;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UpdateProColorDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	public int procolor(int myProColorTypes, int project_id) {
        String sql = "UPDATE project SET color_id = :myProColorTypes WHERE project_id = :project_id";
        params.addValue("myProColorTypes", myProColorTypes);
        params.addValue("project_id", project_id);
        return jdbcTemplate.update(sql, params);
    }
	
	public String getColor(int type) {
		String sql = "SELECT type FROM color WHERE color_id = :type";
		params.addValue("type", type);
		return jdbcTemplate.queryForObject(sql, params, String.class);
	}
}
