package com.mysite.Soda.updateFeed;

import java.sql.Date;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UpdateFeedScheduleDAO {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	public int modifyTitle(String title, int feed_id) {
		String sql = "UPDATE feed_schedule SET title = :title WHERE feed_id = :feed_id";
		params.addValue("title", title);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int modifyStart(Date start, int feed_id) {
		String sql = "UPDATE feed_schedule SET start_date = :start WHERE feed_id = :feed_id";
		params.addValue("start", start);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int modifyEnd(Date end, int feed_id) {
		String sql = "UPDATE feed_schedule SET deadline = :end WHERE feed_id = :feed_id";
		params.addValue("end", end);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int modifyContent(String content, int feed_id) {
		String sql = "UPDATE feed_schedule SET content = :content WHERE feed_id = :feed_id";
		params.addValue("content", content);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int insertReplyLog(String contents, int feed_id, int userNum) {
		String sql = "INSERT INTO reply(reply_id, contents, reply_date, feed_id, member_id) VALUES(SEQ_REPLY.NEXTVAL, :contents, sysdate, :feed_id, :userNum)";
		params.addValue("contents", contents);
		params.addValue("feed_id", feed_id);
		params.addValue("userNum", userNum);
		return jdbcTemplate.update(sql, params);
	}
	
	public int replyPlus(int feed_id) {
		String sql = "UPDATE feed SET reply_cnt = reply_cnt + 1 WHERE feed_id = :feed_id";
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
}
