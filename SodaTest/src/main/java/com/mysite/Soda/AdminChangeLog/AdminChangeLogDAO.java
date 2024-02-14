package com.mysite.Soda.AdminChangeLog;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdminChangeLogDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	RowMapper<AdminChangeLogVO> rowmapper = (rs,rowNum) ->
	new AdminChangeLogVO(
			rs.getString("name"),
			rs.getString("email"),
			rs.getString("menu"),
			rs.getString("object"),
			rs.getString("function"),
			rs.getString("changes"),
			rs.getTimestamp("change_date").toLocalDateTime()
			);

	public List<AdminChangeLogVO> getdownloadlog(int member_id) {
		String sql = "SELECT m.name,m.email,acl.menu,acl.object,acl.function,acl.changes,acl.change_date\r\n"
				+ "FROM admin_change_log acl JOIN member m ON acl.admin_id = m.member_id AND m.member_id = :member_id ORDER BY acl.change_date desc";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.query(sql,params,rowmapper);
	}
}