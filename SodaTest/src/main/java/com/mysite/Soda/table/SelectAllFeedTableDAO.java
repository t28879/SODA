package com.mysite.Soda.table;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysite.Soda.selectFeed.FeedScheduleVO;
import com.mysite.Soda.selectFeed.FeedTextVO;
import com.mysite.Soda.selectFeed.FeedTodoVO;
import com.mysite.Soda.selectFeed.FeedWorkVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SelectAllFeedTableDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	RowMapper<FeedTextVO> TextMapper = (rs, rowNum) ->
		new FeedTextVO(
				rs.getString("profile_image"),
				rs.getString("name"),
				rs.getDate("regist_date"),
				rs.getInt("feed_id"),
				rs.getString("content"),
				rs.getString("title"),
				rs.getInt("member_id")
				);
	
	RowMapper<FeedWorkVO> WorkMapper = (rs, rowNum) ->
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
		
	RowMapper<FeedWorkVO> workmanagerMapper = (rs, rowNum) ->
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
		
		RowMapper<FeedScheduleVO> ScheduleMapper = (rs, rowNum) ->
			new FeedScheduleVO(
					rs.getString("profile_image"),
					rs.getString("name"),
					rs.getDate("regist_date"),
					rs.getDate("start_date"),
					rs.getDate("deadline"),
					rs.getString("title"),
					rs.getString("content"),
					rs.getInt("go"),
					rs.getInt("no"),
					rs.getInt("thinking"),
					rs.getInt("feed_id"),
					rs.getInt("member_id")
					);
		
		RowMapper<FeedTodoVO> TodoMapper = (rs, rowNum) ->
			new FeedTodoVO(
					rs.getString("profile_image"),
					rs.getString("name"),
					rs.getDate("regist_date"),
					rs.getString("title"),
					rs.getInt("feed_id"),
					rs.getInt("finish"),
					rs.getString("content"),
					rs.getDate("deadline"),
					rs.getInt("member_id")
					);
		
	
	public FeedTextVO selectAllFeedText(int feed_id) {
		String sql = "SELECT * FROM feed_text ft JOIN member m ON ft.writer = m.member_id WHERE feed_id = :feed_id";
		Map<String,Object> params = Map.of("feed_id",feed_id);
		return jdbcTemplate.queryForObject(sql, params, TextMapper);
	}	
	
	public FeedWorkVO selectAllFeedWork(int feed_id) {
		String sql = "SELECT m.profile_image, m.name as member_name, fw.regist_date, fw.title, fw.feed_id, fw.process, fw.start_date, fw.deadline, fw.priority, fw.progress, st.sub_process, st.title as sub_title, st.deadline as sub_deadline, st.sub_priority, fw.content, m.member_id, st.sub_task_id \r\n"
				+ "FROM feed_work fw \r\n"
				+ "JOIN member m ON fw.writer = m.member_id\r\n"
				+ "JOIN sub_task st ON st.feed_id = fw.feed_id\r\n"
				+ "WHERE fw.feed_id = :feed_id";
		Map<String,Object> params = Map.of("feed_id",feed_id);
		return jdbcTemplate.queryForObject(sql, params, WorkMapper);
	}
	
	public List<FeedWorkVO> selectAllWorkManager(int feed_id){
		String sql = "SELECT m.profile_image, m.name as member_name, fwm.feed_id, m.member_id\r\n"
				+ "FROM member m JOIN fw_manager fwm ON m.member_id = fwm.member_id\r\n"
				+ "WHERE fwm.feed_id = :feed_id";
		Map<String, Object> param = Map.of("feed_id", feed_id);
		return jdbcTemplate.query(sql, param, workmanagerMapper);
	}
			
	public FeedScheduleVO selectAllFeedSchedule(int feed_id) {
		String sql = "SELECT * FROM feed_schedule fs JOIN member m ON fs.writer = m.member_id WHERE feed_id = :feed_id";
		Map<String,Object> params = Map.of("feed_id",feed_id);
		return jdbcTemplate.queryForObject(sql, params, ScheduleMapper);
	}
	
	public FeedTodoVO selectAllFeedTodo(int feed_id) {
		String sql = "SELECT * FROM feed_todo fd JOIN member m ON fd.writer = m.member_id WHERE feed_id = :feed_id";
		Map<String,Object> params = Map.of("feed_id",feed_id);
		return jdbcTemplate.queryForObject(sql, params, TodoMapper);
	}
}
