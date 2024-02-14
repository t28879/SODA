package com.mysite.Soda.member;

import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	RowMapper<MemberVO> rowmapper = (rs, rowNum) ->
		new MemberVO(
				rs.getInt("member_id"),
				rs.getString("name"),
				rs.getString("phone_num"),
				rs.getString("pw"),
				rs.getString("email"),
				rs.getString("job_name"),
				rs.getInt("sub_department_id"),
				rs.getInt("usage_status"),
				rs.getString("profile_image"),
				rs.getInt("push_onoff"),
				rs.getInt("ip_onoff"),
				rs.getInt("company_id"),
				rs.getTimestamp("join_date").toLocalDateTime(),
				rs.getInt("department_id"),
				rs.getString("status_msg"),
				rs.getInt("company_manager"),
				rs.getString("memo"),
				rs.getInt("ok")
				);
	public MemberVO getMember(int member_id) {
		String sql1 = "SELECT * from member where member_id = :member_id";
		Map<String,Object> param = Map.of("member_id", member_id);
		return jdbcTemplate.queryForObject(sql1, param, rowmapper);
	}
	
}
