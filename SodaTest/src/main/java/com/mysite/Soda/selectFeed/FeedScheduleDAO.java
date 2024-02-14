package com.mysite.Soda.selectFeed;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FeedScheduleDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	RowMapper<FeedScheduleVO> rowMapper = (rs, rowNum) ->
		new FeedScheduleVO(
				rs.getString("profile_image"),
				rs.getString("name"),
				rs.getDate("regist_date"),
				rs.getDate("start_date"),
				rs.getDate("deadline"),
				rs.getString("title"),
				rs.getString("content"),
				rs.getInt("go"),
				rs.getInt("no"),
				rs.getInt("thinking"),
				rs.getInt("feed_id"),
				rs.getInt("member_id")
				);
		
	public List<FeedScheduleVO> projectID(int project_id) {
		String sql = "SELECT m.profile_image, m.name, fs.regist_date, fs.start_date, fs.deadline, fs.title, fs.content, fs.go, fs.no, fs.thinking, fs.feed_id, m.member_id "
				+ "FROM feed_schedule fs  JOIN feed f ON fs.feed_id = f.feed_id "
				+ "JOIN project p ON p.project_id = f.project_id "
				+ "JOIN member m ON fs.writer = m.member_id "
				+ "AND f.type = 3 "
				+ "WHERE f.project_id = :id "
				+ "ORDER BY fs.feed_id DESC";
		Map<String, Object> param = Map.of("id", project_id);
		return jdbcTemplate.query(sql, param, rowMapper);
	}
}
