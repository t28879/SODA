package com.mysite.Soda.selectFeedReply;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FeedScheduleReplyDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	RowMapper<FeedScheduleReplyVO> rowMapper = (rs, rowNum) ->
		new FeedScheduleReplyVO(
				rs.getString("profile_image"),
				rs.getString("contents"),
				rs.getDate("reply_date"),
				rs.getString("name"),
				rs.getInt("feed_id"),
				rs.getInt("reply_id"),
				rs.getInt("member_id")
				);
		
	public List<FeedScheduleReplyVO> projectID(int project_id) {
		String sql = "SELECT m.profile_image, r.contents, r.reply_date, m.name, fs.feed_id, r.reply_id, m.member_id "
				+ "FROM reply r JOIN member m ON r.member_id = m.member_id "
				+ "JOIN feed_schedule fs ON fs.feed_id = r.feed_id "
				+ "JOIN feed f ON f.feed_id = fs.feed_id "
				+ "JOIN project p ON p.project_id = f.project_id "
				+ "WHERE p.project_id = :id "
				+ "ORDER BY r.reply_date DESC";
		Map<String, Object> param = Map.of("id", project_id);
		return jdbcTemplate.query(sql, param, rowMapper);
	}
}
