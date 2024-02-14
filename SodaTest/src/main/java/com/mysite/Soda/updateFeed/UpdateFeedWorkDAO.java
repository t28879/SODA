package com.mysite.Soda.updateFeed;

import java.sql.Date;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UpdateFeedWorkDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	public int modifyTitle(String title, int feed_id) {
		String sql = "UPDATE feed_work SET title = :title WHERE feed_id = :feed_id";
		params.addValue("title", title);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int modifyContent(String content, int feed_id) {
		String sql = "UPDATE feed_work SET content = :content WHERE feed_id = :feed_id";
		params.addValue("content", content);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int modifyProcess(int process, int feed_id) {
		String sql = "UPDATE feed_work SET process = :process WHERE feed_id = :feed_id";
		params.addValue("process", process);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int deleteWorkmanager(int feed_id) {
		String sql = "DELETE FROM fw_manager WHERE feed_id = :feed_id";
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int modifyWorkmanager(int i, int feed_id) {
		String sql = "INSERT INTO fw_manager(feed_id, member_id) VALUES(:feed_id, :i)";
		params.addValue("i", i);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int modifyStart(Date start, int feed_id) {
		String sql = "UPDATE feed_work SET start_date = :start WHERE feed_id = :feed_id";
		params.addValue("start", start);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int modifyEnd(Date end, int feed_id) {
		String sql = "UPDATE feed_work SET deadline = :end WHERE feed_id = :feed_id";
		params.addValue("end", end);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int modifyPriority(int priority, int feed_id) {
		String sql = "UPDATE feed_work SET priority = :priority WHERE feed_id = :feed_id";
		params.addValue("priority", priority);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int modifyProgress(int progress, int feed_id) {
		String sql = "UPDATE feed_work SET progress = :progress WHERE feed_id = :feed_id";
		params.addValue("progress", progress);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int modifyStProcess(int stprocess, int feed_id) {
		String sql = "UPDATE sub_task SET sub_process = :stprocess WHERE feed_id = :feed_id";
		params.addValue("stprocess", stprocess);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int modifyStTitle(String sttitle, int feed_id) {
		String sql = "UPDATE sub_task SET title = :sttile WHERE feed_id = :feed_id";
		params.addValue("sttile", sttitle);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int modifyStEnd(Date stend, int feed_id) {
		String sql = "UPDATE sub_task SET deadline = :stend WHERE feed_id = :feed_id";
		params.addValue("stend", stend);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int modifyStPriority(int stpriority, int feed_id) {
		String sql = "UPDATE sub_task SET sub_priority = :stpriority WHERE feed_id = :feed_id";
		params.addValue("stpriority", stpriority);
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int deleteSfwmanager(int sub_task_id) {
		String sql = "DELETE FROM sub_fw_manager WHERE sub_task_id = :sub_task_id";
		params.addValue("sub_task_id", sub_task_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int modifySubworkmanager(int j, int sub_task_id) {
		String sql = "INSERT INTO sub_fw_manager(sub_task_id, member_id) VALUES(:sub_task_id, :j)";
		params.addValue("j", j);
		params.addValue("sub_task_id", sub_task_id);
		return jdbcTemplate.update(sql, params);
	}
	
	public int insertReplyLog(String contents, int feed_id, int userNum) {
		String sql = "INSERT INTO reply(reply_id, contents, reply_date, feed_id, member_id) VALUES(SEQ_REPLY.NEXTVAL, :contents, sysdate, :feed_id, :userNum)";
		params.addValue("contents", contents);
		params.addValue("feed_id", feed_id);
		params.addValue("userNum", userNum);
		return jdbcTemplate.update(sql, params);
	}
	
	public List<String> getMemberNamesByIds(List<Integer> memberIds) {
	    String sql = "SELECT name as member_name FROM member WHERE member_id IN (:memberIds)";
	    params.addValue("memberIds", memberIds);

	    return jdbcTemplate.queryForList(sql, params, String.class);
	}
	
	public List<String> getSubMembers(List<Integer> memberIds) {
	    String sql = "SELECT name as member_name FROM member WHERE member_id IN (:memberIds)";
	    params.addValue("memberIds", memberIds);

	    return jdbcTemplate.queryForList(sql, params, String.class);
	}
	
	public int replyPlus(int feed_id) {
		String sql = "UPDATE feed SET reply_cnt = reply_cnt + 1 WHERE feed_id = :feed_id";
		params.addValue("feed_id", feed_id);
		return jdbcTemplate.update(sql, params);
	}
}
