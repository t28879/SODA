package com.mysite.Soda.BookMark;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookMarkDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	RowMapper<BookMarkVO> rowmapper = (rs,rowNum) ->
	new BookMarkVO(
			rs.getInt("feed_id"),
			rs.getInt("type")
			);
	RowMapper<FeedTypeVO> allfeedmapper = (rs,rowNum) ->
	new FeedTypeVO(
			rs.getString("title"),
			rs.getString("projectname"),
			rs.getTimestamp("regist_date").toLocalDateTime(),
			rs.getString("name"),
			0,
			0);

	public List<BookMarkVO> getbookmark(int member_id) {
		String sql = "SELECT b.feed_id,f.type\r\n"
				+ "FROM bookmark b\r\n"
				+ "JOIN feed f ON b.feed_id = f.feed_id AND b.member_id = :member_id";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.query(sql,params,rowmapper);
	}
	public FeedTypeVO getfeedtextinfo(int feed_id) {
		String sql = "SELECT ft.regist_date,ft.title,m.name,p.name as projectname\r\n"
				+ "FROM feed_text ft \r\n"
				+ "JOIN member m on ft.writer = m.member_id AND ft.feed_id = :feed_id\r\n"
				+ "JOIN feed f ON ft.feed_id = f.feed_id\r\n"
				+ "JOIN project p ON p.project_id = f.project_id";
		Map<String,Object> params = Map.of("feed_id",feed_id);
		return jdbcTemplate.queryForObject(sql, params, allfeedmapper);
	}
	public FeedTypeVO getfeedworkinfo(int feed_id) {
		String sql = "SELECT ft.regist_date,ft.title,m.name,p.name as projectname\r\n"
				+ "FROM feed_work ft \r\n"
				+ "JOIN member m on ft.writer = m.member_id AND ft.feed_id = :feed_id\r\n"
				+ "JOIN feed f ON ft.feed_id = f.feed_id\r\n"
				+ "JOIN project p ON p.project_id = f.project_id";
		Map<String,Object> params = Map.of("feed_id",feed_id);
		return jdbcTemplate.queryForObject(sql, params, allfeedmapper);
	}
	public FeedTypeVO getfeedscheduleinfo(int feed_id) {
		String sql = "SELECT ft.regist_date,ft.title,m.name,p.name as projectname\r\n"
				+ "FROM feed_schedule ft \r\n"
				+ "JOIN member m on ft.writer = m.member_id AND ft.feed_id = :feed_id\r\n"
				+ "JOIN feed f ON ft.feed_id = f.feed_id\r\n"
				+ "JOIN project p ON p.project_id = f.project_id";
		Map<String,Object> params = Map.of("feed_id",feed_id);
		return jdbcTemplate.queryForObject(sql, params, allfeedmapper);
	}
	public FeedTypeVO getfeedtodoinfo(int feed_id) {
		String sql = "SELECT ft.regist_date,ft.title,m.name,p.name as projectname\r\n"
				+ "FROM feed_todo ft \r\n"
				+ "JOIN member m on ft.writer = m.member_id AND ft.feed_id = :feed_id\r\n"
				+ "JOIN feed f ON ft.feed_id = f.feed_id\r\n"
				+ "JOIN project p ON p.project_id = f.project_id";
		Map<String,Object> params = Map.of("feed_id",feed_id);
		return jdbcTemplate.queryForObject(sql, params, allfeedmapper);
	}
}