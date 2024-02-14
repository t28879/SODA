package com.mysite.Soda.selectMembers;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SelectMembersDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	private final RowMapper<SelectMembersVO> memberMapper = (rs, rowNum) ->
		new SelectMembersVO(
				rs.getString("member_name"),
				rs.getString("company_name"),
				rs.getInt("admin"),
				rs.getString("profile_image"),
				rs.getInt("member_id")
				);
	
	public List<SelectMembersVO> search(String Input, int project_id) {
		String sql = "SELECT m.name AS member_name, c.name AS company_name, pm.admin, m.profile_image, m.member_id \r\n"
				+ "FROM member m, company c, project_member pm, project p \r\n"
				+ "WHERE m.company_id = c.company_id \r\n"
				+ "AND pm.project_id = p.project_id \r\n"
				+ "AND pm.member_id = m.member_id \r\n"
				+ "AND pm.project_id = :project_id AND m.name LIKE :Input ORDER BY pm.admin DESC, m.name ASC";
		params.addValue("project_id", project_id);
		params.addValue("Input", "%" + Input + "%");
		
		return jdbcTemplate.query(sql, params, memberMapper);
	}
}
