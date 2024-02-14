package com.mysite.Soda.selectSearchbar;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SearchDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	private final RowMapper<SearchVO> rowMapperProject = (rs, rowNum) ->
		new SearchVO(
				null, // memberName
		        null, // regist_date
		        rs.getString("projectName"),
		        rs.getString("type"), 
		        null, // title
		        null, // content
		        0,    // feed_type
		        null, // fileNname
		        null,  // quantity
		        rs.getInt("project_id")
		);
	
	private final RowMapper<SearchVO> rowMapperPost = (rs, rowNum) ->
	    new SearchVO(
	    		rs.getString("memberName"),
	            rs.getDate("regist_date"),
	            rs.getString("projectName"),
	            null, // type
	            rs.getString("title"),
	            rs.getString("content"),
	            rs.getInt("feed_type"),
	            null, // fileNname
	            null,  // quantity
	            rs.getInt("project_id")
	    );
	
	private final RowMapper<SearchVO> rowMapperFile = (rs, rowNum) ->
	    new SearchVO(
	    		rs.getString("memberName"),
	            rs.getDate("regist_date"),
	            rs.getString("projectName"),
	            null, // type
	            null, // title
	            null, // content
	            0,    // feed_type
	            rs.getString("fileNname"),
	            rs.getString("quantity"),
	            rs.getInt("project_id")
	    );
		
	public List<SearchVO> searchProjects(String searchInput, int userNum) {
		String sql = "SELECT c.type, p.name as projectName, p.project_id "
				+ "FROM project p, color c, member m, project_member pm  "
				+ "WHERE p.color_id = c.color_id "
				+ "AND pm.member_id = m.member_id "
				+ "AND p.project_id = pm.project_id "
				+ "AND p.name LIKE :searchInput AND pm.member_id = :userNum";

		params.addValue("searchInput", "%" + searchInput + "%");
		params.addValue("userNum", userNum);

		return jdbcTemplate.query(sql, params, rowMapperProject);
	}
	
	public List<SearchVO> searchPosts(String searchInput, int userNum) {
		String sql = "SELECT \r\n"
				+ " title,\r\n"
				+ " content,\r\n"
				+ " m.name AS memberName, \r\n"
				+ " regist_date,\r\n"
				+ " p.name AS projectName,\r\n"
				+ " f.type AS feed_type,\r\n"
				+ " p.project_id\r\n"
				+ "FROM\r\n"
				+ " feed_text ft \r\n"
				+ "JOIN\r\n"
				+ " feed f ON ft.feed_id = f.feed_id \r\n"
				+ "JOIN\r\n"
				+ " member m ON ft.writer = m.member_id\r\n"
				+ "LEFT JOIN \r\n"
				+ " project_member pm ON pm.member_id = m.member_id\r\n"
				+ "LEFT JOIN \r\n"
				+ " project p ON f.project_id = p.project_id \r\n"
				+ "WHERE pm.member_id = :userNum AND (ft.title LIKE :searchInput OR ft.content LIKE :searchInput)\r\n"
				+ "UNION\r\n"
				+ "SELECT \r\n"
				+ " title,\r\n"
				+ " content,\r\n"
				+ " m.name AS memberName, \r\n"
				+ " regist_date,\r\n"
				+ " p.name AS projectName,\r\n"
				+ " f.type AS feed_type,\r\n"
				+ " p.project_id\r\n"
				+ "FROM\r\n"
				+ " feed_work fw \r\n"
				+ "JOIN\r\n"
				+ " feed f ON fw.feed_id = f.feed_id \r\n"
				+ "JOIN\r\n"
				+ " member m ON fw.writer = m.member_id \r\n"
				+ "LEFT JOIN \r\n"
				+ " project_member pm ON pm.member_id = m.member_id\r\n"
				+ "LEFT JOIN \r\n"
				+ " project p ON f.project_id = p.project_id \r\n"
				+ "WHERE pm.member_id = :userNum AND (fw.title LIKE :searchInput OR fw.content LIKE :searchInput)\r\n"
				+ "UNION\r\n"
				+ "SELECT \r\n"
				+ " title,\r\n"
				+ " content,\r\n"
				+ " m.name AS memberName, \r\n"
				+ " regist_date,\r\n"
				+ " p.name AS projectName,\r\n"
				+ " f.type AS feed_type,\r\n"
				+ " p.project_id\r\n"
				+ "FROM\r\n"
				+ " feed_schedule fs \r\n"
				+ "JOIN\r\n"
				+ " feed f ON fs.feed_id = f.feed_id \r\n"
				+ "JOIN\r\n"
				+ " member m ON fs.writer = m.member_id \r\n"
				+ "LEFT JOIN \r\n"
				+ " project_member pm ON pm.member_id = m.member_id\r\n"
				+ "LEFT JOIN \r\n"
				+ " project p ON f.project_id = p.project_id \r\n"
				+ "WHERE pm.member_id = :userNum AND (fs.title LIKE :searchInput OR fs.content LIKE :searchInput)\r\n"
				+ "UNION\r\n"
				+ "SELECT \r\n"
				+ " title,\r\n"
				+ " content,\r\n"
				+ " m.name AS memberName, \r\n"
				+ " regist_date,\r\n"
				+ " p.name AS projectName,\r\n"
				+ " f.type AS feed_type,\r\n"
				+ " p.project_id\r\n"
				+ "FROM\r\n"
				+ " feed_todo fd \r\n"
				+ "JOIN\r\n"
				+ " feed f ON fd.feed_id = f.feed_id \r\n"
				+ "JOIN\r\n"
				+ " member m ON fd.writer = m.member_id \r\n"
				+ "LEFT JOIN \r\n"
				+ " project_member pm ON pm.member_id = m.member_id\r\n"
				+ "LEFT JOIN \r\n"
				+ " project p ON f.project_id = p.project_id \r\n"
				+ "WHERE pm.member_id = :userNum AND (fd.title LIKE :searchInput OR fd.content LIKE :searchInput)";

		params.addValue("searchInput", "%" + searchInput + "%");
        params.addValue("userNum", userNum);

		return jdbcTemplate.query(sql, params, rowMapperPost);
	}
	
	public List<SearchVO> searchFiles(String searchInput, int userNum) {
		String sql = "SELECT fs.name AS fileNname, fs.quantity, m.name AS memberName, fs.regist_date, p.name AS projectName, p.project_id \r\n" + 
				"FROM file_storage fs \r\n" + 
				"JOIN project p ON fs.project_id = p.project_id \r\n" + 
				"JOIN feed f ON fs.feed_id = f.feed_id AND f.project_id = p.project_id\r\n" + 
				"JOIN member m ON fs.member_id = m.member_id\r\n" + 
				"JOIN project_member pm ON pm.member_id = m.member_id AND f.project_id = pm.project_id\r\n" + 
				"WHERE pm.member_id = :userNum AND fs.name LIKE :searchInput";

		params.addValue("userNum", userNum);
		params.addValue("searchInput", "%" + searchInput + "%");

		return jdbcTemplate.query(sql, params, rowMapperFile);
	}
	
}
