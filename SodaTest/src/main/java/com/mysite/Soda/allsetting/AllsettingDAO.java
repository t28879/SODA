package com.mysite.Soda.allsetting;

import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AllsettingDAO {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	RowMapper<AllsettingVO> rowmapper = (rs, rowNum) ->
		new AllsettingVO(
			rs.getInt("member_id"),	
			rs.getString("backgroundimg")	
			);
	
		public AllsettingVO getAllsetting(int member_id) {
			String sql = "select member_id, backgroundimg from all_setting where member_id = :member_id";
			Map<String,Object> param = Map.of("member_id", member_id);
			return jdbcTemplate.queryForObject(sql, param, rowmapper);	
		}
		
		public Integer getseletedproject(int member_id) {
			String sql = "SELECT SELECTED_PROJECT FROM member WHERE member_id = :member_id";
			Map<String,Object> params = Map.of("member_id",member_id);
			try {
		        return jdbcTemplate.queryForObject(sql, params, Integer.class);
		    } catch (EmptyResultDataAccessException e) {
		        return null;
		    }
		}
}
