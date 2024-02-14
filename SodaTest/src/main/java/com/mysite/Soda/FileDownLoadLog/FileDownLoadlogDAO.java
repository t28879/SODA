package com.mysite.Soda.FileDownLoadLog;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FileDownLoadlogDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	RowMapper<FileDownLoadLogVO> watermapper = (rs,rowNum) ->
	new FileDownLoadLogVO(
			rs.getTimestamp("downloads_date").toLocalDateTime(),
			rs.getString("filename"),
			rs.getString("quantity"),
			rs.getString("name"),
			rs.getString("email")
			);

	public List<FileDownLoadLogVO> getdownloadlog(int member_id) {
		String sql = "SELECT fd.downloads_date,fs.name as \"filename\",fs.quantity,m.name,m.email\r\n"
				+ "FROM file_download_log fd JOIN member m ON fd.member_id = m.member_id AND m.company_id = (SELECT company_id FROM member WHERE member_id = :member_id) \r\n"
				+ "JOIN file_storage fs ON fd.file_id = fs.file_id";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.query(sql,params,watermapper);
	}
}