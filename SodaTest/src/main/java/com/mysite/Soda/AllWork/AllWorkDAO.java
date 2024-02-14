package com.mysite.Soda.AllWork;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysite.Soda.project.ProjectVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AllWorkDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	
	RowMapper<ProjectVO> rowMapper = (rs, rowNum) -> 
	new ProjectVO(
			rs.getInt("project_id"),
			rs.getString("name"),
			0,
			null,
			0,
			0,
			0,
			0,
			0,
			0,
			null,
			0,
			null,
			null);
	
	RowMapper<FeedWorkVO> feedworkMapper = (rs, rowNum) -> 
	new FeedWorkVO(
			rs.getInt("project_id"),
			rs.getInt("feed_id"),
			rs.getInt("process"),
			rs.getInt("priority"),
			rs.getDate("start_date"),
			rs.getDate("deadline"),
			rs.getInt("progress"),
			rs.getString("title"),
			rs.getString("content"),
			rs.getInt("writer"),
			rs.getDate("regist_date"));
	RowMapper<FeedWorkManagerVO> feedworkmanagerMapper = (rs, rowNum) -> 
	new FeedWorkManagerVO(
			rs.getInt("member_id"),
			rs.getString("name"),null,null);
	RowMapper<FeedWorkManagerVO> feedworkmanagerdetialMapper = (rs, rowNum) -> 
	new FeedWorkManagerVO(
			rs.getInt("member_id"),
			rs.getString("name"),
			rs.getString("email"),
			rs.getString("deptname"));
	
	RowMapper<SubWorkVO> subworkMapper = (rs, rowNum) -> 
	new SubWorkVO(
			rs.getInt("feed_id"),
			rs.getInt("sub_task_id"),
			rs.getString("title"),
			rs.getInt("sub_process"),
			rs.getDate("deadline"),
			rs.getDate("start_date"),
			rs.getInt("progress"),
			rs.getInt("member_id"),
			rs.getInt("sub_priority"));
	
	public List<ProjectVO> getmyprojectinfo(int member_id) {
		String sql = "SELECT DISTINCT(p.name),p.project_id\r\n"
				+ "FROM project p\r\n"
				+ "JOIN project_member pm ON p.project_id = pm.project_id AND pm.member_id = :member_id "
				+ "JOIN feed f ON f.project_id = p.project_id AND f.type = 2";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.query(sql,params,rowMapper);
	}
	public List<FeedWorkVO> getfeedwork(int project_id) {
		String sql = "SELECT fw.*,f.project_id\r\n"
				+ "FROM feed f \r\n"
				+ "JOIN feed_work fw ON fw.feed_id = f.feed_id AND f.project_id = :project_id AND f.type = 2";
		Map<String,Object> params = Map.of("project_id",project_id);
		return jdbcTemplate.query(sql,params,feedworkMapper);
	}
	public List<SubWorkVO> getsubwork(int feed_id) {
		String sql = "SELECT fw.feed_id,st.*\r\n"
				+ "FROM sub_task st \r\n"
				+ "JOIN feed_work fw ON st.feed_id = fw.feed_id AND fw.feed_id = 1";
		Map<String,Object> params = Map.of("feed_id",feed_id);
		return jdbcTemplate.query(sql,params,subworkMapper);
	}
	public List<FeedWorkManagerVO> getworkmanagerlist(int feed_id) {
		String sql = "SELECT m.member_id,m.name\r\n"
				+ "FROM fw_manager fwm \r\n"
				+ "JOIN member m ON fwm.member_id = m.member_id AND fwm.feed_id = :feed_id";
		Map<String,Object> params = Map.of("feed_id",feed_id);
		return jdbcTemplate.query(sql,params,feedworkmanagerMapper);
	}
	public List<FeedWorkManagerVO> getsubworkmanagerlist(int feed_id) {
		String sql = "SELECT m.member_id,m.name\r\n"
				+ "FROM SUB_FW_MANAGER fwm \r\n"
				+ "JOIN member m ON fwm.member_id = m.member_id AND fwm.SUB_TASK_ID = :feed_id";
		Map<String,Object> params = Map.of("feed_id",feed_id);
		return jdbcTemplate.query(sql,params,feedworkmanagerMapper);
	}
	public List<FeedWorkManagerVO> getworkmanagerdetail(int feed_id) {
		String sql = "SELECT m.member_id,m.name,m.email,d.name as deptname\r\n"
				+ "FROM feed_work fw\r\n"
				+ "JOIN fw_manager fwm ON fw.feed_id = fwm.feed_id AND fw.feed_id = :feed_id  \r\n"
				+ "JOIN member m ON m.member_id = fwm.member_id\r\n"
				+ "JOIN departments d ON m.department_id = d.department_id";
		Map<String,Object> params = Map.of("feed_id",feed_id);
		return jdbcTemplate.query(sql,params,feedworkmanagerdetialMapper);
	}
	public List<FeedWorkManagerVO> getsubworkmanagerdetail(int feed_id) {
		String sql = "SELECT m.member_id,m.name,m.email,d.name as deptname\r\n"
				+ "FROM sub_task fw\r\n"
				+ "JOIN sub_fw_manager fwm ON fw.sub_task_id = fwm.sub_task_id AND fw.sub_task_id = :feed_id\r\n"
				+ "JOIN member m ON m.member_id = fwm.member_id\r\n"
				+ "JOIN departments d ON m.department_id = d.department_id";
		Map<String,Object> params = Map.of("feed_id",feed_id);
		return jdbcTemplate.query(sql,params,feedworkmanagerdetialMapper);
	}
	public int updatefeedprocess(int feed_id,int process) {
		String sql = "UPDATE feed_work SET process = :process WHERE feed_id = :feed_id";
		params.addValue("feed_id", feed_id);
		params.addValue("process", process);
		return jdbcTemplate.update(sql, params);
	}
	public int updatesubfeedprocess(int feed_id,int process) {
		String sql = "UPDATE sub_task SET sub_process = :process WHERE sub_task_id = :feed_id";
		params.addValue("feed_id", feed_id);
		params.addValue("process", process);
		return jdbcTemplate.update(sql, params);
	}
	public int updatefeedpriority(int feed_id,int priority) {
		String sql = "UPDATE feed_work SET priority = :priority WHERE feed_id = :feed_id";
		params.addValue("feed_id", feed_id);
		params.addValue("priority", priority);
		return jdbcTemplate.update(sql, params);
	}
	public int updatesubfeedpriority(int feed_id,int priority) {
		String sql = "UPDATE sub_task SET sub_priority = :priority WHERE sub_task_id = :feed_id";
		params.addValue("feed_id", feed_id);
		params.addValue("priority", priority);
		return jdbcTemplate.update(sql, params);
	}
	public int updateFeedSDate(String selectedDate,int selectedId) {
		String sql = "UPDATE feed_work SET start_date = :selectedDate WHERE feed_id = :selectedId";
		params.addValue("selectedDate", selectedDate);
		params.addValue("selectedId", selectedId);
		return jdbcTemplate.update(sql, params);
	}
	public int updateFeedDDate(String selectedDate,int selectedId) {
		String sql = "UPDATE feed_work SET deadline = :selectedDate WHERE feed_id = :selectedId";
		params.addValue("selectedDate", selectedDate);
		params.addValue("selectedId", selectedId);
		return jdbcTemplate.update(sql, params);
	}
	public int updateSubFeedSDate(String selectedDate,int selectedId) {
		String sql = "UPDATE sub_task SET start_date = :selectedDate WHERE sub_task_id = :selectedId";
		params.addValue("selectedDate", selectedDate);
		params.addValue("selectedId", selectedId);
		return jdbcTemplate.update(sql, params);
	}
	public int updateSubFeedDDate(String selectedDate,int selectedId) {
		String sql = "UPDATE sub_task SET deadline = :selectedDate WHERE sub_task_id = :selectedId";
		params.addValue("selectedDate", selectedDate);
		params.addValue("selectedId", selectedId);
		return jdbcTemplate.update(sql, params);
	}
	public int updateFeedProgress(int selectfeedid,int percentage) {
		String sql = "UPDATE feed_work SET progress = :percentage WHERE feed_id = :selectfeedid";
		params.addValue("percentage", percentage);
		params.addValue("selectfeedid", selectfeedid);
		return jdbcTemplate.update(sql, params);
	}
	public int updateSubFeedProgress(int selectfeedid,int percentage) {
		String sql = "UPDATE sub_task SET progress = :percentage WHERE sub_task_id = :selectfeedid";
		params.addValue("percentage", percentage);
		params.addValue("selectfeedid", selectfeedid);
		return jdbcTemplate.update(sql, params);
	}
	public List<FeedWorkManagerVO> getpreworkmanagerdetail(int feed_id) {
		String sql = "SELECT m.member_id,m.name,m.email,d.name as deptname\r\n"
				+ "FROM member m \r\n"
				+ "JOIN project_member pm ON m.member_id = pm.member_id AND pm.project_id = \r\n"
				+ "(SELECT f.project_id\r\n"
				+ "FROM feed_work fw JOIN feed f ON f.feed_id = fw.feed_id AND fw.feed_id = :feed_id)\r\n"
				+ "JOIN departments d ON m.department_id = d.department_id";
		Map<String,Object> params = Map.of("feed_id",feed_id);
		return jdbcTemplate.query(sql,params,feedworkmanagerdetialMapper);
	}
	public List<FeedWorkManagerVO> getpresubworkmanagerdetail(int feed_id) {
		String sql = "SELECT m.member_id,m.name,m.email,d.name as deptname\r\n"
				+ "FROM member m \r\n"
				+ "JOIN project_member pm ON m.member_id = pm.member_id AND pm.project_id = \r\n"
				+ "(SELECT f.project_id\r\n"
				+ "FROM sub_task st \r\n"
				+ "JOIN feed_work fw ON st.sub_task_id = fw.sub_task_id AND st.sub_task_id = :feed_id \r\n"
				+ "JOIN feed f ON f.feed_id = fw.feed_id)\r\n"
				+ "JOIN departments d ON m.department_id = d.department_id";
		Map<String,Object> params = Map.of("feed_id",feed_id);
		return jdbcTemplate.query(sql,params,feedworkmanagerdetialMapper);
	}
	public int deletesubfeedmanager(int selectedId) {
		String sql = "DELETE FROM sub_fw_manager WHERE sub_task_id = :selectedId";
		params.addValue("selectedId", selectedId);
		return jdbcTemplate.update(sql, params);
	}
	public int deletefeedmanager(int selectedId) {
		String sql = "DELETE FROM fw_manager WHERE feed_id = :selectedId";
		params.addValue("selectedId", selectedId);
		return jdbcTemplate.update(sql, params);
	}
	public int updatefeedmanager(int selectedId,int member_id) {
		String sql = "INSERT INTO fw_manager VALUES(:selectedId,:member_id)";
		params.addValue("selectedId", selectedId);
		params.addValue("member_id", member_id);
		return jdbcTemplate.update(sql, params);
	}
	public int updatesubfeedmanager(int selectedId,int member_id) {
		String sql = "INSERT INTO sub_fw_manager VALUES(:selectedId,:member_id)";
		params.addValue("selectedId", selectedId);
		params.addValue("member_id", member_id);
		return jdbcTemplate.update(sql, params);
	}
	public int updatereply(int feed_id,int member_id,String content) {
		String sql = "INSERT INTO reply VALUES(SEQ_REPLY.NEXTVAL,:content,sysdate,:feed_id,:member_id)";
		params.addValue("member_id", member_id);
		params.addValue("feed_id", feed_id);
		params.addValue("content", content);
		return jdbcTemplate.update(sql, params);
	}
	public FeedWorkVO getfeedworkinfo(int feed_work_id) {
		String sql = "SELECT f.project_id, fw.*\r\n"
				+ "FROM feed_work fw\r\n"
				+ "JOIN feed f ON fw.feed_id = f.feed_id AND fw.feed_id = :feed_work_id";
		params.addValue("feed_work_id", feed_work_id);
		return jdbcTemplate.queryForObject(sql, params,feedworkMapper);
	}
	public SubWorkVO getsubworkinfo(int sub_task_id) {
		String sql = "SELECT fw.feed_id,st.*\r\n"
				+ "FROM sub_task st \r\n"
				+ "JOIN feed_work fw ON st.sub_task_id = fw.sub_task_id AND st.sub_task_id = :sub_task_id";
		params.addValue("sub_task_id", sub_task_id);
		return jdbcTemplate.queryForObject(sql, params,subworkMapper);
	}
	
}