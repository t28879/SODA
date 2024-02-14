package com.mysite.Soda.insertFeed;

import java.sql.Date;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FeedDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	// 피드 ISNERT
	public int feed(int project_id, int type) {
        String sql = "INSERT INTO feed(feed_id, project_id, type, reply_cnt, likey_cnt) VALUES(SEQ_FEED_ID.NEXTVAL, :project_id, :type, DEFAULT, DEFAULT)";
        params.addValue("project_id", project_id);
        params.addValue("type", type);
        return jdbcTemplate.update(sql, params);
    }
	
	// 피드 글 ISNERT
    public int feedtext(String content, String title, int userNum) {
        // feed 메서드에서 NEXTVAL을 호출한 이후에 CURRVAL을 사용
        String sql = "INSERT INTO feed_text(feed_id, content, title, regist_date, writer) VALUES(SEQ_FEED_ID.CURRVAL, :content, :title, sysdate, :userNum)";
        params.addValue("content", content);
        params.addValue("title", title);
        params.addValue("userNum", userNum);
        return jdbcTemplate.update(sql, params);
    }
    
    // 피드 업무 ISNERT
    public int feedWork(int process, int priority,
    					Date start_date, Date deadline,
    					int progress, String title,
    					String content, int userNUm) {
    	String sql = "INSERT INTO feed_work(feed_id, process, priority, start_date, deadline, progress, title, content, writer, regist_date) VALUES(SEQ_FEED_ID.CURRVAL, :process, :priority, :start_date, :deadline, :progress, :title, :content, :userNUm, sysdate)";
    	params.addValue("process", process);
    	params.addValue("priority", priority);
    	params.addValue("start_date", start_date);
    	params.addValue("deadline", deadline);
    	params.addValue("progress", progress);
    	params.addValue("title", title);
    	params.addValue("content", content);
    	params.addValue("userNUm", userNUm);
    	return jdbcTemplate.update(sql, params);
    }
    
    // 피드 업무의 업무 매니저 INSERT
    public int fwManager(int member_id) {
    	String sql = "INSERT INTO fw_manager(feed_id, member_id) VALUES(SEQ_FEED_ID.CURRVAL, :member_id)";
    	params.addValue("member_id", member_id);
    	return jdbcTemplate.update(sql, params);
    }
    
    // 피드 업무의 하위업무 ISNERT
    public int subtask(String subtaskInput, int subProcess, Date subDeadline, int subProgress, int userNum, int subPriority, Date subStart_date) {
    	String sql = "INSERT INTO sub_task(sub_task_id, title, sub_process, deadline, progress, member_id, sub_priority, start_date, feed_id) VALUES(SEQ_SUBTASK_ID.NEXTVAL,  :subtaskInput, :subProcess, :subDeadline, :subProgress, :userNum, :subPriority, :subStart_date, SEQ_FEED_ID.CURRVAL)";
    	params.addValue("subtaskInput", subtaskInput);
    	params.addValue("subProcess", subProcess);
    	params.addValue("subDeadline", subDeadline);
    	params.addValue("subProgress", subProgress);
    	params.addValue("userNum", userNum);
    	params.addValue("subPriority", subPriority);
    	params.addValue("subStart_date", subStart_date);
    	return jdbcTemplate.update(sql, params);
    }
    
    // 피드 업무의 하위업무 매니저 INSERT
    public int stfwmanager(int member_id) {
    	String sql = "INSERT INTO sub_fw_manager(sub_task_id, member_id) VALUES(SEQ_SUBTASK_ID.CURRVAL, :member_id)";
    	params.addValue("member_id", member_id);
    	return jdbcTemplate.update(sql, params);
    }
    
    // 피드 일정 INSERT
    public int feedSchedule(Date start_date, Date deadline, String title, String content, int userNum, int go, int no, int thinking) {
    	String sql = "INSERT INTO feed_schedule(feed_id, start_date, deadline, regist_date, title, content, writer, go, no, thinking) VALUES(SEQ_FEED_ID.CURRVAL, :start_date, :deadline, sysdate, :title, :content, :userNum, :go, :no, :thinking)";
    	params.addValue("start_date", start_date);
    	params.addValue("deadline", deadline);
    	params.addValue("title", title);
    	params.addValue("content", content);
    	params.addValue("userNum", userNum);
    	params.addValue("go", go);
    	params.addValue("no", no);
    	params.addValue("thinking", thinking);
    	return jdbcTemplate.update(sql, params);
    }
    
    // 피드 할일 ISNERT
    public int feedTodo(String todoInput, int finish, Date deadline, String title, int userNum) {
    	String sql = "INSERT INTO feed_todo(feed_id, content, regist_date, finish, deadline, title, writer) VALUES(SEQ_FEED_ID.CURRVAL, :todoInput, sysdate, :finish, :deadline, :title, :userNum)";
    	params.addValue("todoInput", todoInput);
    	params.addValue("finish", finish);
    	params.addValue("deadline", deadline);
    	params.addValue("title", title);
    	params.addValue("userNum", userNum);
    	return jdbcTemplate.update(sql, params);
    }
}
