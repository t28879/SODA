package com.mysite.Soda.updateFeed;

import java.sql.Date;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UpdateFeedTodoDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	public int modifyTitle(int feed_id, String title) {
		String sql = "UPDATE feed_todo SET title = :title WHERE feed_id = :feed_id";
		params.addValue("title", title);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int modifyEnd(int feed_id, Date end) {
		String sql = "UPDATE feed_todo SET deadline = :end WHERE feed_id = :feed_id";
		params.addValue("end", end);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int modifyEnd(int feed_id, String content) {
		String sql = "UPDATE feed_todo SET content = :content WHERE feed_id = :feed_id";
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
