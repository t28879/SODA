package com.mysite.Soda.selectFeed;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FeedWorkDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	RowMapper<FeedWorkVO> rowMapper = (rs, rowNum) ->
		new FeedWorkVO(
				rs.getString("profile_image"),
				rs.getString("member_name"),
				rs.getDate("regist_date"),
				rs.getString("title"),
				rs.getInt("feed_id"),
				rs.getInt("process"),
				rs.getDate("start_date"),
				rs.getDate("deadline"),
				rs.getInt("priority"),
				rs.getInt("progress"),
				rs.getInt("sub_process"),
				rs.getString("sub_title"),
				rs.getDate("sub_deadline"),
				rs.getInt("sub_priority"),
				rs.getString("content"),
				rs.getInt("member_id"),
				rs.getInt("sub_task_id")
				);
	
	public List<FeedWorkVO> projectID(int project_id) {
		String sql = "SELECT m.profile_image, m.name as member_name, fw.regist_date, fw.title, fw.feed_id, fw.process, fw.start_date, fw.deadline, fw.priority, fw.progress, st.sub_process, st.title as sub_title, st.deadline as sub_deadline, st.sub_priority  , fw.content, m.member_id,st.sub_task_id "
				+ "FROM feed_work fw JOIN feed f ON fw.feed_id = f.feed_id "
				+ "JOIN project p ON p.project_id = f.project_id "
				+ "JOIN member m ON fw.writer = m.member_id "
				+ "JOIN sub_task st ON st.feed_id = fw.feed_id "
				+ "AND f.type = 2 "
				+ "WHERE f.project_id = :id "
				+ "ORDER BY fw.feed_id DESC";
		Map<String, Object> param = Map.of("id", project_id);
		return jdbcTemplate.query(sql, param, rowMapper);
	}
	
	RowMapper<FeedWorkVO> rowMapperManager = (rs, rowNum) ->
	new FeedWorkVO(
			rs.getString("profile_image"),
			rs.getString("member_name"),
			null,
			null,
			rs.getInt("feed_id"),
			0,
			null,
			null,
			0,
			0,
			0,
			null,
			null,
			0,
			null,
			rs.getInt("member_id"),
			0
			);
	
	public List<FeedWorkVO> feedID(int feed_id) {
		String sql = "SELECT m.profile_image, m.name as member_name, fwm.feed_id, m.member_id\r\n"
				+ "FROM member m JOIN fw_manager fwm ON m.member_id = fwm.member_id\r\n"
				+ "WHERE fwm.feed_id = :feed_id";
		Map<String, Object> param = Map.of("feed_id", feed_id);
		return jdbcTemplate.query(sql, param, rowMapperManager);
	}
	
}
