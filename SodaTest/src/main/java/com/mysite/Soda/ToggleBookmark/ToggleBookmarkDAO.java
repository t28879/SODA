package com.mysite.Soda.ToggleBookmark;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ToggleBookmarkDAO {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	public boolean isBookmark(int feed_id, int userNum) {
		String sql = "SELECT COUNT(*) FROM bookmark WHERE feed_id = :feed_id AND member_id = :userNum";
		params.addValue("feed_id", feed_id);
		params.addValue("userNum", userNum);
		
		int cnt = jdbcTemplate.queryForObject(sql, params, Integer.class);
		
		return cnt > 0;
	}
	
	public int insertBookmark(int feed_id, int userNum) {
		String sql = "INSERT INTO bookmark(feed_id, member_id) VALUES(:feed_id, :userNum)";
		params.addValue("feed_id", feed_id);
		params.addValue("userNum", userNum);
		return jdbcTemplate.update(sql, params);
	}
	
	public int deleteBookmark(int feed_id, int userNum) {
		String sql = "DELETE FROM bookmark WHERE feed_id = :feed_id AND member_id = :userNum";
		params.addValue("feed_id", feed_id);
		params.addValue("userNum", userNum);
		return jdbcTemplate.update(sql, params);
	}
	
}
