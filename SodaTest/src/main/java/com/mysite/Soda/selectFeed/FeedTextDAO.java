package com.mysite.Soda.selectFeed;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FeedTextDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	RowMapper<FeedTextVO> rowMapper = (rs, rowNum) ->
		new FeedTextVO(
				rs.getString("profile_image"),
				rs.getString("name"),
				rs.getDate("regist_date"),
				rs.getInt("feed_id"),
				rs.getString("content"),
				rs.getString("title"),
				rs.getInt("member_id")
				);
		
	public List<FeedTextVO> projectID(int project_id) {
		String sql = "SELECT m.profile_image, m.name, ft.regist_date, ft.feed_id, ft.content, ft.title, m.member_id "
				+ "FROM feed_text ft JOIN feed f ON ft.feed_id = f.feed_id "
				+ "JOIN project p ON p.project_id = f.project_id "
				+ "JOIN member m ON ft.writer = m.member_id "
				+ "AND f.type = 1 "
				+ "WHERE f.project_id = :id "
				+ "ORDER BY ft.feed_id DESC";
		Map<String, Object> param = Map.of("id", project_id);
		return jdbcTemplate.query(sql, param, rowMapper);
	}
}
