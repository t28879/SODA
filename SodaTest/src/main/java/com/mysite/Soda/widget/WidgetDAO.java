package com.mysite.Soda.widget;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class WidgetDAO {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	RowMapper<WidgetVO> rowmapper = (rs, rowNum) ->
		new WidgetVO(
			rs.getString("widget_name"),
			rs.getInt("order_column")
			);
		
		public List<WidgetVO> getWidget(int member_id) {
			String sql = "select widget_name, order_column from widget where member_id = :member_id order by order_column";
			Map<String,Object> param = Map.of("member_id", member_id);
			return jdbcTemplate.query(sql, param, rowmapper);	
		}
}
