package com.mysite.Soda.deleteFeedPro;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DeleteFeedProDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	public boolean thisAdmin(int project_id, int userNum) {
	    String sql = "SELECT admin\r\n"
	            + "FROM project_member\r\n"
	            + "WHERE member_id = :userNum\r\n"
	            + "AND project_id = :project_id";
	    params.addValue("userNum", userNum);
	    params.addValue("project_id", project_id);
	    try {
	        Integer admin = jdbcTemplate.queryForObject(sql, params, Integer.class);
	        return admin != null && admin == 1;
	    } catch (EmptyResultDataAccessException e) {
	        return false;
	    }
	}
	
	public int deletrPro(int project_id, int userNum) {
	    String sql = "DELETE FROM project\n"
	            + "WHERE project_id = :project_id\n"
	            + "AND :userNum IN (\n"
	            + "    SELECT member_id\n"
	            + "    FROM project_member\n"
	            + "    WHERE project_id = :project_id\n"
	            + "    AND admin = 1\n"
	            + ")";
	    params.addValue("project_id", project_id);
	    params.addValue("userNum", userNum);
	    return jdbcTemplate.update(sql, params);
	}
}
