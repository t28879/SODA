package com.mysite.Soda.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysite.Soda.Join.FindCompanyService;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProjectDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	
	RowMapper<ProjectMemberVO> rowMapperpm = (rs,rowNum) ->
		new ProjectMemberVO(
				rs.getInt("member_id"),
				rs.getString("name"),
				rs.getString("email"),
				rs.getString("deptname"));
		RowMapper<ProjectAdminListVO> adminrapper = (rs,rowNum) ->
		new ProjectAdminListVO(
				rs.getLong("member_id"));	
		
	RowMapper<ProjectVO> rowMapper = (rs, rowNum) -> 
		new ProjectVO(
				rs.getInt("project_id"),
				rs.getString("name"),
				rs.getInt("authority"),
				rs.getTimestamp("project_date").toLocalDateTime(),
				rs.getInt("per_onoff"),
				rs.getInt("tag_id"),
				rs.getInt("calendar_id"),
				rs.getInt("color_id"),
				rs.getInt("company_id"),
				rs.getInt("register"),
				rs.getString("url"),
				rs.getInt("pro_cnt"),
				rs.getString("visible"),
				rs.getTimestamp("recent_update").toLocalDateTime());
		
	RowMapper<ProjectcntVO> rowMapperCnt = (rs, rowNum) ->
	new ProjectcntVO(
			rs.getInt("'cnt1'"),
			rs.getInt("'cnt2'"),
			rs.getInt("'cnt3'"),
			rs.getInt("'cnt4'"),
			rs.getInt("'cnt5'"),
			rs.getInt("'cnt6'")
			);
	
	RowMapper<ProjectAdminVO> rowMapperAdmin = (rs, rowNum) ->
	new ProjectAdminVO(
			rs.getString("name"),
			rs.getString("email"),
			rs.getString("deptname"),
			rs.getString("projectname"),
			rs.getInt("project_id"),
			rs.getInt("member_id")
			);
	
	public List<ProjectinfoVO> findAllProject(int member_id) {
		
		List<ProjectVO> projectidList = getprojectid(member_id);
		List<ProjectinfoVO> allprojectinfo = new ArrayList<ProjectinfoVO>(projectidList.size());
		for(ProjectVO projectVO : projectidList) { 
			ProjectinfoVO projectinfoVO = new ProjectinfoVO();
			int project_id = projectVO.getProject_id();
			projectinfoVO.setProject_id(project_id);
			projectinfoVO.setName(projectVO.getName());
			projectinfoVO.setProject_date(projectVO.getProject_date());
			List<ProjectMemberVO> projectmlist = getprojectManagerName(project_id);//각 projectMemberVO에서 get메서드 사용해서 추출해야함
			if(projectmlist.size() == 1) {
				projectinfoVO.setProjectmanager(projectmlist.get(0).getName());
			}
			else if(projectmlist.size() != 0){
				projectinfoVO.setProjectmanager(projectmlist.get(0).getName() + "외"+(projectmlist.size()-1)+"명");
			}
			else {
				projectinfoVO.setProjectmanager("없음");
			}
			ProjectcntVO procntVO = getprojectcnt(project_id);
			projectinfoVO.setAllfeed(procntVO.getCnt1());
			projectinfoVO.setAllreply(procntVO.getCnt2());
			projectinfoVO.setAllmember(procntVO.getCnt3());
			projectinfoVO.setAllchatting(procntVO.getCnt4());
			projectinfoVO.setAllschedule(procntVO.getCnt5());
			projectinfoVO.setAllwork(procntVO.getCnt6());
			allprojectinfo.add(projectinfoVO);
		}
		
		return allprojectinfo;
    }
	public List<ProjectVO> getprojectid(int member_id) {
		String sql = "SELECT * FROM project where company_id = (SELECT company_id FROM member WHERE member_id = :member_id)";
		Map<String,Object> param = Map.of("member_id",member_id);
		
		return jdbcTemplate.query(sql,param,rowMapper);
	}
	public List<ProjectMemberVO> getprojectManagerName(int project_id) {
		String sql = "SELECT m.email,m.member_id,m.name,d.name as deptname FROM project_member pm JOIN member m ON pm.member_id = m.member_id AND pm.project_id = :project_id AND pm.admin = 1"
				+ " JOIN departments d ON m.department_id = d.department_id";
		Map<String,Object> param = Map.of("project_id",project_id);
		
		return jdbcTemplate.query(sql,param,rowMapperpm);
	}
	public ProjectcntVO getprojectcnt(int project_id) {
		String sql = "SELECT *\r\n"
				+ "FROM (\r\n"
				+ "    SELECT 'cnt1' AS category, COUNT(*) AS count_val FROM feed WHERE project_id = :project_id\r\n"
				+ "    UNION ALL\r\n"
				+ "    SELECT 'cnt2' AS category, COUNT(r.reply_id) AS count_val FROM feed f JOIN reply r ON f.feed_id = r.feed_id AND f.project_id = :project_id\r\n"
				+ "    UNION ALL\r\n"
				+ "    SELECT 'cnt3' AS category, COUNT(*) FROM project_member WHERE project_id = :project_id\r\n"
				+ "    UNION ALL\r\n"
				+ "    SELECT 'cnt4' AS category, COUNT(*) FROM chatting_main WHERE project_id = :project_id\r\n"
				+ "    UNION ALL\r\n"
				+ "    SELECT 'cnt5' AS category, COUNT(*) FROM feed f JOIN feed_schedule fs ON f.feed_id = fs.feed_id AND f.project_id = :project_id\r\n"
				+ "    UNION ALL\r\n"
				+ "    SELECT 'cnt6' AS category, COUNT(*) FROM feed f JOIN feed_work fw ON f.feed_id = fw.feed_id AND f.project_id = :project_id\r\n"
				+ ") \r\n"
				+ "PIVOT (\r\n"
				+ "    MAX(count_val) FOR category IN ('cnt1', 'cnt2','cnt3','cnt4','cnt5','cnt6')\r\n"
				+ ")";
		Map<String,Object> param = Map.of("project_id",project_id);
		
		return jdbcTemplate.queryForObject(sql,param,rowMapperCnt);
	}
	
	public List<ProjectAdminVO> getAdminList(int project_id){
		String sql = "SELECT m.member_id,m.name,m.email,d.name as deptname,p.name as projectname,p.project_id"
				+ " FROM member m "
				+ " JOIN project_member pm ON m.member_id = pm.member_id AND pm.admin = 1 AND pm.project_id = :project_id"
				+ " JOIN project p ON pm.project_id = p.project_id"
				+ " JOIN departments d ON m.department_id = d.department_id";
		Map<String,Object> param = Map.of("project_id",project_id);
		return jdbcTemplate.query(sql, param,rowMapperAdmin);
	}
	
	public ProjectVO getprojectinfo(int project_id) {
		String sql = "SELECT * FROM project WHERE project_id = :project_id";
		Map<String,Object> param = Map.of("project_id",project_id);
		return jdbcTemplate.queryForObject(sql, param, rowMapper);
	}
	
	public List<ProjectMemberVO> getpremanager(int project_id) {
		String sql = "SELECT m.member_id,m.name,m.email,d.name as deptname\r\n"
				+ "FROM project_member pm \r\n"
				+ "JOIN member m ON pm.member_id = m.member_id AND pm.project_id = :project_id\r\n"
				+ "JOIN departments d ON m.department_id = d.department_id";
		Map<String,Object> param = Map.of("project_id",project_id);
		return jdbcTemplate.query(sql, param, rowMapperpm);
	}
	public int refreshprojectmember(int project_id) {
		String sql = "UPDATE project_member SET admin = 0 WHERE project_id = :project_id ";
		Map<String,Object> param = Map.of("project_id",project_id);
		return jdbcTemplate.update(sql, param);
	}
	public int updateprojectname(String name,int project_id) {
		String sql = "UPDATE project SET name = :name WHERE project_id = :project_id";
		params.addValue("name", name);
		params.addValue("project_id", project_id);
		return jdbcTemplate.update(sql, params);
	}
	public int updateprojectAdmin(Long member_id,int project_id) {
		String sql = "UPDATE project_member SET admin = 1 WHERE project_id = :project_id AND member_id = :member_id";
		params.addValue("member_id", member_id);
		params.addValue("project_id", project_id);
		return jdbcTemplate.update(sql, params);
	}
	public List<ProjectAdminListVO> getoriginalList(int project_id){
		String sql = "SELECT member_id \r\n"
				+ "FROM project_member WHERE project_id = :project_id AND admin = 1";
		Map<String,Object> params = Map.of("project_id",project_id);
		return jdbcTemplate.query(sql, params,adminrapper);
	}
	public int AdminChangeLog(int member_id,String full,String change) {
		String sql = "INSERT INTO admin_change_log values(:member_id,'프로젝트 관리','프로젝트 관리자 권한 변경',:full,:change,sysdate)";
		params.addValue("member_id", member_id);
		params.addValue("full", full);
        params.addValue("change", change);
        return jdbcTemplate.update(sql, params);
    }
	
	public List<ProjectVO> getProject(int member_id) {
		String sql = "SELECT * from project p, project_member pm where pm.project_id = p.project_id and pm.member_id = :member_id";
		Map<String,Object> param = Map.of("member_id", member_id);
		return jdbcTemplate.query(sql, param, rowMapper);	
	}
	
	public int getColor() {
		Random rd = new Random();
		int color = 0;
		for(int i=0; i<12; i++) {
			color = rd.nextInt(12)+1;
		}
		return color;
	}
	
	public int getProjectId() {
		String sql = "SELECT SEQ_PROJECT.CURRVAL FROM DUAL";
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}
	
	private final FindCompanyService findCompanyService;
	public Integer companyId(Integer member_id) {
		int comId = findCompanyService.getComId(member_id);
		return comId;
	}
	
	public int insertProject(int member_id, String projectName) {
		String sql = "INSERT INTO project (project_id, name, authority, project_date, per_onoff, tag_id, calendar_id, color_id, company_id, register, url, pro_cnt, visible, recent_update)"
					+ " VALUES(SEQ_PROJECT.NEXTVAL, :projectName, 1, sysdate, 1, null, null, " + getColor() + ", " + companyId(member_id) + ", :member_id, null, null, null, sysdate)";
		Map<String,Object> param = Map.of("projectName",projectName,"member_id",member_id);
		return jdbcTemplate.update(sql, param);
	}
	
	public int insertProjectMember(int member_id, int project_id) {
		String sql = "INSERT INTO project_member (project_id, member_id, admin) VALUES(:project_id, :member_id, 1)";
		Map<String,Object> param = Map.of("project_id",project_id,"member_id",member_id);
		return jdbcTemplate.update(sql, param);
	}
}