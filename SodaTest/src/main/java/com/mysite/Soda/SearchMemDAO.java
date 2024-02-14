package com.mysite.Soda;

import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SearchMemDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
		RowMapper<MemberVO> memrowMapper = (rs,rowNum) ->
		new MemberVO(
				rs.getInt("member_id"),
				rs.getString("name"),
				rs.getString("deptname"),
				rs.getString("jobname"),
				rs.getString("email"),
				rs.getInt("company_id"),
				rs.getString("phone_num"),
				rs.getString("profile_image"));
		RowMapper<MemberLongTypeVO> memlongrowMapper = (rs,rowNum) ->
		new MemberLongTypeVO(
				rs.getLong("member_id"),
				rs.getString("name"),
				rs.getString("deptname"),
				rs.getString("jobname"),
				rs.getString("email"),
				rs.getInt("company_id"),
				rs.getString("phone_num"));

	public MemberVO searchmeminfo(int member_id) {
		String query = "SELECT m.profile_image,m.member_id,m.name,d.name as deptname,m.job_name as jobname,m.email,m.company_id,m.phone_num \r\n"
				+ "FROM member m JOIN departments d ON m.department_id = d.department_id AND m.member_id = :member_id";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.queryForObject(query, params, memrowMapper);
	}
	public MemberLongTypeVO searchmeminfoLongtype(Long member_id) {
		String query = "SELECT m.member_id,m.name,d.name as deptname,m.job_name as jobname,m.email,m.company_id,m.phone_num \r\n"
				+ "FROM member m JOIN departments d ON m.department_id = d.department_id AND m.member_id = :member_id";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.queryForObject(query, params, memlongrowMapper);
	}
	public MemberVO searchmeminfobyemail(String email) {
		String query = "SELECT m.member_id,m.name,d.name as deptname,m.job_name as jobname,m.email,m.company_id,m.phone_num \r\n"
				+ "FROM member m JOIN departments d ON m.department_id = d.department_id AND m.email = :email";
		Map<String,Object> params = Map.of("email",email);
		return jdbcTemplate.queryForObject(query, params, memrowMapper);
	}
}