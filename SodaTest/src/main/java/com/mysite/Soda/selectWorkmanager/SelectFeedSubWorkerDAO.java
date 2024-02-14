package com.mysite.Soda.selectWorkmanager;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SelectFeedSubWorkerDAO {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();

	RowMapper<SelectFeedSubWorkerVO> rowMapper = (rs, rowNum) -> new SelectFeedSubWorkerVO(
			rs.getString("profile_image"), rs.getString("member_name"), rs.getString("company_name"));

	public List<SelectFeedSubWorkerVO> subworker(int sub_task_id) {
		String sql = "SELECT m.profile_image, m.name as member_name, c.name as company_name "
				+ "FROM sub_fw_manager sfm JOIN member m ON sfm.member_id = m.member_id "
				+ "JOIN sub_task st ON st.sub_task_id = sfm.sub_task_id "
				+ "JOIN company c ON c.company_id = m.company_id " + "WHERE sfm.sub_task_id = :sub_task_id";
		params.addValue("sub_task_id", sub_task_id);
		return jdbcTemplate.query(sql, params, rowMapper);
	}
}
