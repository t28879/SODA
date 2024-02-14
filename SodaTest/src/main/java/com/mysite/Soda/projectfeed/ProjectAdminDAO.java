package com.mysite.Soda.projectfeed;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProjectAdminDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	RowMapper<ProjectAdminVO> rowMapper = (rs, rowNum) ->
		new ProjectAdminVO(
				rs.getString("member_name"),
				rs.getString("company_name"),
				rs.getInt("admin"),
				rs.getString("profile_image"),
				rs.getInt("member_id")
				);
	
	public List<ProjectAdminVO> projectID(int project_id) {
		String sql = "SELECT m.name AS member_name, c.name AS company_name, pm.admin, m.profile_image, m.member_id "
				+ "FROM member m, company c, project_member pm, project p "
				+ "WHERE m.company_id = c.company_id "
				+ "AND pm.project_id = p.project_id "
				+ "AND pm.member_id = m.member_id "
				+ "AND pm.project_id = :id ORDER BY pm.admin DESC, m.name ASC";
		Map<String, Object> param = Map.of("id", project_id);
		return jdbcTemplate.query(sql, param, rowMapper);
	}
}
