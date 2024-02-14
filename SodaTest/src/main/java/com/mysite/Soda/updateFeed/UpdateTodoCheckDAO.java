package com.mysite.Soda.updateFeed;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UpdateTodoCheckDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	public int updatecheck(int feed_id, int isChecked) {
		String sql = "UPDATE feed_todo SET finish = :isChecked WHERE feed_id = :feed_id";
		params.addValue("isChecked", isChecked);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int checkbox(int feed_id) {
		String sql = "SELECT finish FROM feed_todo WHERE feed_id = :feed_id";
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}
}
