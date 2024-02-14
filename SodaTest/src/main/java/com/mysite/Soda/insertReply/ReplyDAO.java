package com.mysite.Soda.insertReply;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	public int reply(int feed_id, int userNum, String commentText) {
		String sql = "INSERT INTO reply(reply_id, contents, reply_date, feed_id, member_id) VALUES(SEQ_REPLY.NEXTVAL, :commentText, sysdate, :feed_id, :userNum)";
		params.addValue("commentText", commentText);
		params.addValue("feed_id", feed_id);
		params.addValue("userNum", userNum);
		return jdbcTemplate.update(sql, params);
	}
	
	public int updateReplyCnt(int feed_id) {
		String sql = "UPDATE feed\r\n"
				+ "SET reply_cnt = reply_cnt + 1\r\n"
				+ "WHERE feed_id = :feed_id";
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
}
