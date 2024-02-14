package com.mysite.Soda.selectWorkmanager;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SelectWorkmanagerDAO {
	private final NamedParameterJdbcTemplate jdbcTemplate;

	RowMapper<SelectWorkmanagerVO> rowMapper = (rs, rowNum) -> 
		new SelectWorkmanagerVO(
				rs.getString("profile_image"),
				rs.getString("member_name"),
				rs.getString("company_name"),
				rs.getInt("member_id")
				);

	public List<SelectWorkmanagerVO> project_id(int project_id) {
		String sql = "SELECT m.profile_image, m.name as member_name, c.name as company_name, m.member_id\r\n"
				+ "FROM member m JOIN project_member pm ON m.member_id = pm.member_id\r\n"
				+ "JOIN company c ON c.company_id = m.company_id\r\n"
				+ "WHERE pm.project_id = :project_id";
		Map<String, Object> param = Map.of("project_id", project_id);
		return jdbcTemplate.query(sql, param, rowMapper);
	}
}
