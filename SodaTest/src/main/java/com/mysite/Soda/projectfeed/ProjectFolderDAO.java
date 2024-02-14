package com.mysite.Soda.projectfeed;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProjectFolderDAO {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	RowMapper<ProjectFolderVO> rowMapper = (rs, rowNum) ->
		new ProjectFolderVO (
				rs.getString("name")
				);
		
	public List<ProjectFolderVO> project_id(int project_id) {
		String sql = "SELECT pfn.name \r\n"
				+ "FROM project_folder_name pfn \r\n"
				+ "WHERE NOT EXISTS (\r\n"
				+ "    SELECT 1 \r\n"
				+ "    FROM project_folder pf \r\n"
				+ "    WHERE pf.project_id = :id\r\n"
				+ "      AND pf.folder_id = pfn.folder_id\r\n"
				+ ")\r\n"
				+ "AND pfn.folder_id NOT IN (\r\n"
				+ "    SELECT folder_id\r\n"
				+ "    FROM project_folder\r\n"
				+ "    WHERE project_id = :id\r\n"
				+ ")";
		Map<String, Object> param = Map.of("id", project_id);
		return jdbcTemplate.query(sql, param, rowMapper);
	}
}
