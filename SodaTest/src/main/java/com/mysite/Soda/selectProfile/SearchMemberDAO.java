package com.mysite.Soda.selectProfile;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SearchMemberDAO {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	private final RowMapper<SearchMemberVO> rowMapper = (rs, rowNum) ->
		new SearchMemberVO(
				rs.getString("profile_image"),
				rs.getString("member_name"),
				rs.getString("company_name"),
				rs.getString("email"),
				rs.getString("phone_num"),
				rs.getString("status_msg")
				);
		
		
	public SearchMemberVO searchMember(int member_id) {
		String sql = "SELECT m.profile_image, m.name as member_name, c.name as company_name, m.email, m.phone_num, m.status_msg\r\n"
				+ "FROM member m JOIN company c ON m.company_id = c.company_id \r\n"
				+ "WHERE m.member_id = :member_id";
		params.addValue("member_id", member_id);
		return jdbcTemplate.queryForObject(sql, params, rowMapper);
	}
}
