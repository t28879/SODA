package com.mysite.Soda.FileStorage;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FileStorageDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	RowMapper<FileStorageVO> rowmapper = (rs,rowNum) ->
	new FileStorageVO(
			rs.getString("file_name"),
			rs.getString("quantity"),
			rs.getString("name"),
			rs.getString("email"),
			rs.getTimestamp("regist_date").toLocalDateTime()
			);

	public List<FileStorageVO> getfilestorage(int member_id) {
		String sql = "SELECT fs.name as file_name, fs.quantity, m.name, m.email,fs.regist_date \r\n"
				+ "FROM member m \r\n"
				+ "JOIN file_storage fs ON m.member_id = fs.member_id \r\n"
				+ "JOIN project p ON p.project_id = fs.project_id AND p.project_id IN \r\n"
				+ "(SELECT project_id from project_member WHERE member_id = :member_id)";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.query(sql,params,rowmapper);
	}
}