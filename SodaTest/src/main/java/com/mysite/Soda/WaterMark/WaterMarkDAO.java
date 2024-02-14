package com.mysite.Soda.WaterMark;

import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class WaterMarkDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	RowMapper<WaterMarkVO> watermapper = (rs,rowNum) ->
	new WaterMarkVO(
			rs.getInt("WM")
			);

	public WaterMarkVO getwatermark(int member_id) {
		String sql = "SELECT WM FROM company WHERE company_id = (SELECT company_id FROM member WHERE member_id = :member_id)";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.queryForObject(sql,params,watermapper);
	}
	public int alterwm(int member_id, int wm) {
		String sql = "UPDATE company SET WM = :wm WHERE company_id = (SELECT company_id FROM member WHERE member_id = :member_id)";
		params.addValue("wm", wm);
		params.addValue("member_id", member_id);
		return jdbcTemplate.update(sql, params);
	}
}