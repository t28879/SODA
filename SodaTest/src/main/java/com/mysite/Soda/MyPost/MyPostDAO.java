package com.mysite.Soda.MyPost;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyPostDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	RowMapper<MyPostVO> mapper = (rs,rowNum) ->
	new MyPostVO(
			rs.getInt("feed_id")
			);
	RowMapper<MyReplyVO> replymapper = (rs,rowNum) ->
	new MyReplyVO(
			rs.getString("contents"),
			rs.getInt("feed_id"),
			rs.getTimestamp("reply_date").toLocalDateTime()
			);
	public List<MyPostVO> getmypost(int member_id) {
		String sql = "SELECT feed_id\r\n"
				+ "FROM (\r\n"
				+ "    SELECT feed_id\r\n"
				+ "    FROM feed_work\r\n"
				+ "    WHERE writer = :member_id\r\n"
				+ "    UNION\r\n"
				+ "    SELECT feed_id\r\n"
				+ "    FROM feed_text\r\n"
				+ "    WHERE writer = :member_id\r\n"
				+ "    UNION\r\n"
				+ "    SELECT feed_id\r\n"
				+ "    FROM feed_todo\r\n"
				+ "    WHERE writer = :member_id\r\n"
				+ "    UNION\r\n"
				+ "    SELECT feed_id\r\n"
				+ "    FROM feed_schedule\r\n"
				+ "    WHERE writer = :member_id\r\n"
				+ ")";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.query(sql,params,mapper);
	}
	public String getfeedtitle(int feed_id) {
		String sql = "SELECT title\r\n"
				+ "FROM(\r\n"
				+ "SELECT title \r\n"
				+ "FROM feed_schedule\r\n"
				+ "WHERE feed_id = :feed_id\r\n"
				+ "UNION\r\n"
				+ "SELECT title \r\n"
				+ "FROM feed_text\r\n"
				+ "WHERE feed_id = :feed_id\r\n"
				+ "UNION\r\n"
				+ "SELECT title \r\n"
				+ "FROM feed_todo\r\n"
				+ "WHERE feed_id = :feed_id\r\n"
				+ "UNION\r\n"
				+ "SELECT title \r\n"
				+ "FROM feed_work\r\n"
				+ "WHERE feed_id = :feed_id\r\n"
				+ ")";
		Map<String,Object> params = Map.of("feed_id",feed_id);
		return jdbcTemplate.queryForObject(sql,params,String.class);
	}
	public List<MyReplyVO> getreplylist(int member_id){
		String sql = "SELECT contents,reply_date,feed_id\r\n"
				+ "FROM reply \r\n"
				+ "WHERE member_id = :member_id";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.query(sql, params,replymapper);
	}
	public int getfeedtype(int feed_id) {
		String sql = "SELECT type FROM feed WHERE feed_id = :feed_id";
		Map<String,Object> params = Map.of("feed_id",feed_id);
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}
	public int getprojectid(int feed_id) {
		String sql = "SELECT project_id FROM feed WHERE feed_id = :feed_id";
		Map<String,Object> params = Map.of("feed_id",feed_id);
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}
}