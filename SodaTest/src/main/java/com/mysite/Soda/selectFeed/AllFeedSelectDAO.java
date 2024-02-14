package com.mysite.Soda.selectFeed;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AllFeedSelectDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	RowMapper<AllFeedSelectVO> feedMapper = (rs, Num) ->
		new AllFeedSelectVO(
				rs.getInt("feed_id"),
				rs.getInt("project_id"),
				rs.getInt("type"),
				rs.getInt("reply_cnt"),
				rs.getInt("likey_cnt")
				);
	
	public List<AllFeedSelectVO> selectfeed(int project_id) {
		String sql = "SELECT feed_id, project_id, type, reply_cnt, likey_cnt FROM feed WHERE project_id = :project_id";
		Map<String, Object> param = Map.of("project_id" , project_id);
		return jdbcTemplate.query(sql, param, feedMapper);
	}
}
