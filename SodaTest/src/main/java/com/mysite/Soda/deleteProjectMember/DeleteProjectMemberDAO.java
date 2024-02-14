package com.mysite.Soda.deleteProjectMember;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DeleteProjectMemberDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	public boolean isAdmin(int userNum, int project_id) {
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
	
	public int projectAdmin(int project_id) {
		String sql = "SELECT COUNT(member_id) FROM project_member WHERE project_id = :project_id AND admin = 1";
		params.addValue("project_id", project_id);
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}
	
	
	public int deleteProjectMember(int project_id, int userNum) {
        String sql = "DELETE FROM project_member\n"
                + "WHERE project_id = :project_id\n"
                + "  AND member_id = :userNum\n"
                + "  AND (\n"
                + "    SELECT COUNT(*)\n"
                + "    FROM project_member\n"
                + "    WHERE project_id = :project_id\n"
                + "  ) > 1";
        params.addValue("project_id", project_id);
        params.addValue("userNum", userNum);
        return jdbcTemplate.update(sql, params);
    }
	
}
