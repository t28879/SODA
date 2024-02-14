package com.mysite.Soda.feedwork;

import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ExpectedFwcountDAO {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	RowMapper<ExpectedFwcountVO> rowmapper = (rs, rowNum) ->
		new ExpectedFwcountVO(
			rs.getInt("count")
			);
	
		public ExpectedFwcountVO getExpectedFwcount(int member_id) {
			String sql = "select COUNT(f.feed_id) as count "
					+ "from feed f, feed_work fw, fw_manager fwm, project_member pm "
					+ "where pm.member_id = fwm.member_id and pm.project_id = f.project_id "
					+ "and fwm.feed_id = f.feed_id and fwm.feed_id = fw.feed_id "
					+ "and fwm.member_id = :member_id and fw.process <= 3 and fw.deadline >= sysdate and fw.start_date >= trunc(sysdate, 'month')";

			Map<String,Object> param = Map.of("member_id", member_id);
			return jdbcTemplate.queryForObject(sql, param, rowmapper);	
		}
}
