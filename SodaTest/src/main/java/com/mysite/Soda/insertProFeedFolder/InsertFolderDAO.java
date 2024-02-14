package com.mysite.Soda.insertProFeedFolder;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class InsertFolderDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	public InsertFolderDTO selectfolder(String folderName, int project_id) {
        String sql = "SELECT pfn.folder_id " +
                     "FROM project_folder_name pfn " +
                     "WHERE pfn.name = :folderName";
        params.addValue("folderName", folderName);
        return jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(InsertFolderDTO.class));
    }
	
	public int insertfolder(int folder_id, int project_id) {
        String sql = "INSERT INTO project_folder (folder_id, project_id) VALUES(:folder_id, :project_id)";
        params.addValue("folder_id", folder_id);
        params.addValue("project_id", project_id);
        return jdbcTemplate.update(sql, params);
    }
}
