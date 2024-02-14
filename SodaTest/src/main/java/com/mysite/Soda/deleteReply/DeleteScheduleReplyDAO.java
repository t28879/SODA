package com.mysite.Soda.deleteReply;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DeleteScheduleReplyDAO {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();

	public int thisMember(int reply_id, int userNum) {
		String sql = "SELECT m.member_id\r\n" + "FROM reply r JOIN  member m ON r.member_id = m.member_id\r\n"
				+ "WHERE r.reply_id = :reply_id\r\n" + "AND m.member_id = :userNum";
		params.addValue("userNum", userNum);
		params.addValue("reply_id", reply_id);

		try {
			Integer member_id = jdbcTemplate.queryForObject(sql, params, Integer.class);
			return member_id != null && member_id.equals(userNum) ? member_id : 0;
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

	public int deletereply(int reply_id, int userNum) {
		String sql = "DELETE FROM reply WHERE reply_id = :reply_id AND member_id = :userNum";
		params.addValue("reply_id", reply_id);
		params.addValue("userNum", userNum);
		return jdbcTemplate.update(sql, params);
	}
	
	public int updatereplycnt(int feed_id) {
		String sql = "UPDATE feed SET reply_cnt = reply_cnt - 1 WHERE feed_id = :feed_id";
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
}
