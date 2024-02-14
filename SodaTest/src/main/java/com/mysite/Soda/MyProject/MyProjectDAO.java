package com.mysite.Soda.MyProject;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysite.Soda.project.ProjectVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyProjectDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	
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
	RowMapper<MyProjectFolderVO> fileMapper = (rs, rowNum) -> 
	new MyProjectFolderVO(
			rs.getInt("folder_id"),
			rs.getString("name"),
			rs.getTimestamp("recent_date").toLocalDateTime());
	RowMapper<ProjectFolderVO> pfMapper = (rs, rowNum) -> 
	new ProjectFolderVO(
			rs.getInt("folder_id"),
			rs.getInt("project_id"));
	
	public List<ProjectVO> getmyprojectinfo(int member_id) {
		String sql = "SELECT p.* \r\n"
				+ "FROM project p\r\n"
				+ "JOIN project_member pm ON p.project_id = pm.project_id AND pm.member_id = :member_id";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.query(sql,params,rowMapper);
	}
	public int updatemyproject(int color_id,int project_id) {
		String sql = "UPDATE PROJECT SET color_id = :color_id WHERE project_id = :project_id";
		params.addValue("color_id", color_id);
		params.addValue("project_id", project_id);
        return jdbcTemplate.update(sql, params);
    }
	public int insertmyprojectfolder(int folder_id,int project_id) {
		String sql = "INSERT INTO PROJECT_FOLDER VALUES(:folder_id,:project_id)";
		params.addValue("folder_id", folder_id);
		params.addValue("project_id", project_id);
        return jdbcTemplate.update(sql, params);
    }
	public boolean searchmyprojectfolder(int folder_id,int project_id) {
		String sql = "SELECT folder_id FROM PROJECT_FOLDER WHERE folder_id = :folder_id AND project_id = :project_id";
		params.addValue("folder_id", folder_id);
		params.addValue("project_id", project_id);
		try {
			jdbcTemplate.queryForObject(sql, params,Integer.class);
	        return true; 
	    } catch (EmptyResultDataAccessException e) {
	        return false; 
	    } catch (IncorrectResultSizeDataAccessException e) {
	        return false;
	    } catch (DataAccessException e) {
	        return false;
	    }
    }
	public List<MyProjectFolderVO> getmyprojectfolderlist(int member_id) {
		String sql = "SELECT folder_id,name,recent_date\r\n"
				+ "FROM project_folder_name WHERE member_id = :member_id";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.query(sql,params,fileMapper);
	}
}