package com.mysite.Soda.selectFeed;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FeedTodoDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	RowMapper<FeedTodoVO> rowMapper = (rs, rowNum) ->
		new FeedTodoVO(
				rs.getString("profile_image"),
				rs.getString("name"),
				rs.getDate("regist_date"),
				rs.getString("title"),
				rs.getInt("feed_id"),
				rs.getInt("finish"),
				rs.getString("content"),
				rs.getDate("deadline"),
				rs.getInt("member_id")
				);
	
	public List<FeedTodoVO> projectID(int project_id) {
		String sql = "SELECT m.profile_image, m.name, fd.regist_date, fd.title, fd.feed_id, fd.finish, fd.content, fd.deadline, m.member_id "
				+ "FROM feed_todo fd  JOIN feed f ON fd.feed_id = f.feed_id "
				+ "JOIN project p ON p.project_id = f.project_id "
				+ "JOIN member m ON fd.writer = m.member_id "
				+ "AND f.type = 4 "
				+ "WHERE f.project_id = :id "
				+ "ORDER BY fd.feed_id DESC";
		Map<String, Object> param = Map.of("id", project_id);
		return jdbcTemplate.query(sql, param, rowMapper);
	}
}
