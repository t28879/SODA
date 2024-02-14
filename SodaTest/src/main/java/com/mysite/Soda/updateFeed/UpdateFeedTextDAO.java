package com.mysite.Soda.updateFeed;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UpdateFeedTextDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	public int modifyTitle(String feedModifyTitle, int feed_id) {
		String sql = "UPDATE feed_text SET title = :feedModifyTitle WHERE feed_id = :feed_id";
		params.addValue("feedModifyTitle", feedModifyTitle);
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
	
	public int modifyContent(String modifyContent, int feed_id) {
		String sql = "UPDATE feed_text SET content = :modifyContent WHERE feed_id = :feed_id";
		params.addValue("modifyContent", modifyContent);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int replyplus(int feed_id) {
		String sql = "UPDATE feed SET reply_cnt = reply_cnt + 1 WHERE feed_id = :feed_id";
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
}
