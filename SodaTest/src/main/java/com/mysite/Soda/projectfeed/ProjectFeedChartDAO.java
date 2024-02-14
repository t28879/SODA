package com.mysite.Soda.projectfeed;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProjectFeedChartDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	RowMapper<Integer> rowMapper = (rs, rowNum) ->
		rs.getInt("process");
			
	
	public List<Integer> projectID(int project_id) {
		String sql = "SELECT fw.process \r\n"
				+ "FROM feed_work fw \r\n"
				+ "JOIN feed f ON fw.feed_id = f.feed_id\r\n"
				+ "WHERE f.project_id = :id\r\n"
				+ "AND f.type = 2";
		Map<String, Object> param = Map.of("id", project_id);
		return jdbcTemplate.query(sql, param,rowMapper);
	}
	
	
}
