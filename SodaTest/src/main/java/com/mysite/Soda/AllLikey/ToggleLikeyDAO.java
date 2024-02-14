package com.mysite.Soda.AllLikey;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ToggleLikeyDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	public boolean isLiked(int feed_id, int userNum) {
	    String sql = "SELECT COUNT(*) FROM likey WHERE feed_id = :feed_id AND member_id = :userNum";

	    params.addValue("feed_id", feed_id);
	    params.addValue("userNum", userNum);

	    // 쿼리 결과를 int로 받아옴
	    int count = jdbcTemplate.queryForObject(sql, params, Integer.class);

	    // count가 0보다 크면 true, 아니면 false 반환
	    return count > 0;
	}
	
	public int insertLike(int feed_id, int userNum) {
        String sql = "INSERT INTO likey (feed_id, member_id) VALUES (:feed_id, :userNum)";
        params.addValue("feed_id", feed_id);
        params.addValue("userNum", userNum);
        return jdbcTemplate.update(sql, params);
    }
	
	public int updatereplyplus(int feed_id) {
		String sql = "UPDATE feed SET likey_cnt = likey_cnt + 1 WHERE feed_id = :feed_id";
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int deleteLike(int feed_id, int userNum) {
	    String sql = "DELETE FROM likey WHERE feed_id = :feed_id AND member_id = :userNum";
	    
	    params.addValue("feed_id", feed_id);
	    params.addValue("userNum", userNum);
	    
	    return jdbcTemplate.update(sql, params);
	}
	
	public int updatereplyminus(int feed_id) {
		String sql = "UPDATE feed SET likey_cnt = likey_cnt - 1 WHERE feed_id = :feed_id";
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
}
