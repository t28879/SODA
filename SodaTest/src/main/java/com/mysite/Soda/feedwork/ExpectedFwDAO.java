package com.mysite.Soda.feedwork;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ExpectedFwDAO {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	RowMapper<ExpectedFwVO> rowmapper = (rs, rowNum) ->
		new ExpectedFwVO(
			rs.getInt("process"),	
			rs.getString("title"),
			rs.getInt("priority"),
			rs.getString("deadline")
			);
	
		public List<ExpectedFwVO> getExpectedFw(int member_id) {
			String sql = "select fw.process, fw.title, fw.priority, TO_CHAR(fw.deadline, 'MM-DD') AS deadline "
					+ "from feed f, feed_work fw, fw_manager fwm, project_member pm " 
					+ "where pm.member_id = fwm.member_id and pm.project_id = f.project_id and fwm.feed_id = f.feed_id "
					+ "and fwm.feed_id = fw.feed_id and fw.process <= 3 and fwm.member_id = :member_id and fw.deadline >= sysdate and fw.start_date >= trunc(sysdate, 'month')";
			Map<String,Object> param = Map.of("member_id", member_id);
			return jdbcTemplate.query(sql, param, rowmapper);	
		}
}
