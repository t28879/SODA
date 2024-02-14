package com.mysite.Soda.deleteFeed;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DeleteFeedTextDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	public int deletefeed(int feed_id) {
		String sql = "DELETE FROM feed WHERE feed_id = :feed_id";
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}

}
