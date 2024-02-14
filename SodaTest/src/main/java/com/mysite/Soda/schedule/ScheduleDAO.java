package com.mysite.Soda.schedule;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ScheduleDAO {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	RowMapper<ScheduleVO> rowmapper = (rs, rowNum) ->
		new ScheduleVO(
			rs.getString("title"),
			rs.getString("start_date"),
			rs.getString("deadline")
			);
		
		public List<ScheduleVO> getSchedule(int member_id, String clickedDate) {
			String sql = "select fs.title, fs.start_date, fs.deadline from feed_schedule fs, feed f, project_member pm where fs.feed_id = f.feed_id and f.project_id = pm.project_id and pm.member_id = :member_id and fs.deadline >= :clickedDate and fs.start_date <= :clickedDate order by fs.deadline";
			Map<String,Object> param = Map.of("member_id", member_id, "clickedDate", clickedDate);
			return jdbcTemplate.query(sql, param, rowmapper);	
		}
}

