package com.mysite.Soda.color;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ColorDAO {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	RowMapper<ColorVO> rowmapper = (rs, rowNum) ->
		new ColorVO(
			rs.getString("type"),	
			rs.getString("name"),
			rs.getInt("project_id")
			);
	
		RowMapper<ColorDetailVO> colormapper = (rs, rowNum) ->
		new ColorDetailVO(
			rs.getString("type")
			);
		
		public List<ColorVO> getColor(int member_id) {
			String sql = "select c.type, p.name, p.project_id from color c, project p, project_member pm where p.project_id = pm.project_id and p.color_id = c.color_id and pm.member_id = :member_id order by p.recent_update desc";
			Map<String,Object> param = Map.of("member_id", member_id);
			return jdbcTemplate.query(sql, param, rowmapper);	
		}
		public ColorDetailVO getColorType(int color_id) {
			String sql = "SELECT type FROM color WHERE color_id = :color_id";
			Map<String,Object> param = Map.of("color_id",color_id);
			return jdbcTemplate.queryForObject(sql, param,colormapper );
		}
		
}
