package com.mysite.Soda.folder;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FolderDAO {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	RowMapper<FolderVO> rowmapper = (rs, rowNum) ->
		new FolderVO(
			rs.getString("name")	
			);
	
		public List<FolderVO> getFolder(int member_id) {
			String sql = "select name from project_folder_name where member_id = :member_id order by recent_date desc";
			Map<String,Object> param = Map.of("member_id", member_id);
			return jdbcTemplate.query(sql, param, rowmapper);	
		}
}
