package com.mysite.Soda.selectProjectName;

import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SelectProjectNameDAO {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	RowMapper<SelectProjectNameVO> rowmapper = (rs, rowNum) ->
		new SelectProjectNameVO(
			rs.getString("name")
			);
		
		public SelectProjectNameVO getSelectProjectName(int clickedId) {
			String sql = "select name from project where project_id = :clickedId";
			Map<String,Object> param = Map.of("clickedId", clickedId);
			return jdbcTemplate.queryForObject(sql, param, rowmapper);	
		}
}