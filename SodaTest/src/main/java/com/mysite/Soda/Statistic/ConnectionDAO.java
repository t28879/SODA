package com.mysite.Soda.Statistic;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ConnectionDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	
	RowMapper<ConnectionVO> rowMapperpm = (rs,rowNum) ->
		new ConnectionVO(
				rs.getString("name"),
				rs.getString("deptname"),
				rs.getString("job_name"),
				rs.getString("email"),
				rs.getString("phone_num"),
				rs.getTimestamp("join_date").toLocalDateTime(),
				rs.getInt("count"), 0, 0, 0, 0);
		RowMapper<ConnectionVO> rowMap = (rs,rowNum) ->
		new ConnectionVO(
				rs.getString("name"),
				rs.getString("deptname"),
				rs.getString("job_name"),
				rs.getString("email"),
				rs.getString("phone_num"),
				rs.getTimestamp("join_date").toLocalDateTime(),
				0,
				rs.getInt("pcount"),
				rs.getInt("cmtcount"),
				rs.getInt("crcount"),
				rs.getInt("msgcount"));

	public List<ConnectionVO> getconstatisticinfo(int member_id) {
		String sql = "SELECT m.member_id,m.name,d.name as deptname,m.job_name,m.email,m.phone_num,m.join_date,COUNT(m.name) as count"
				+ " FROM member m "
				+ " JOIN login_report lr ON m.member_id = lr.member_id AND lr.company_id = (SELECT company_id FROM member WHERE member_id = :member_id)"
				+ " JOIN departments d ON m.department_id = d.department_id"
				+ " GROUP BY m.member_id, m.name, d.name, m.job_name, m.email, m.phone_num, m.join_date";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.query(sql,params,rowMapperpm);
	}
	public List<ConnectionVO> getusestatisticinfo(int member_id) {
		String sql = "SELECT m.member_id,m.name,d.name as deptname,m.job_name,m.email,m.phone_num,m.join_date,SUM(lr.P_CNT) as pcount,\r\n"
				+ "SUM(lr.cmt_cnt)as cmtcount,SUM(lr.cr_cnt)as crcount,SUM(lr.msg_cnt)as msgcount\r\n"
				+ "FROM member m \r\n"
				+ "JOIN login_report lr ON m.member_id = lr.member_id AND lr.company_id = (SELECT company_id FROM member WHERE member_id = :member_id)\r\n"
				+ "JOIN departments d ON m.department_id = d.department_id\r\n"
				+ "GROUP BY m.member_id, m.name, d.name, m.job_name, m.email, m.phone_num, m.join_date";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.query(sql,params,rowMap);
	}
}