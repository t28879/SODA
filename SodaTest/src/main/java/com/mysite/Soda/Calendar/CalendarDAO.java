package com.mysite.Soda.Calendar;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysite.Soda.MyPost.MyPostVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CalendarDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	RowMapper<CalendarVO> mapper = (rs,rowNum) ->
	new CalendarVO(
			rs.getDate("start_date"),
			rs.getDate("deadline"),
			rs.getString("title")
			);
	RowMapper<IndividualVO> indimapper = (rs,rowNum) ->
	new IndividualVO(
			rs.getTimestamp("start_date").toLocalDateTime(),
			rs.getString("title"),
			rs.getInt("individualid")
			);
	
	public List<CalendarVO> modifymember(int member_id) {
		String sql = "SELECT * \r\n"
				+ "FROM feed_schedule fs\r\n"
				+ "JOIN feed f ON fs.feed_id = f.feed_id\r\n"
				+ "JOIN project_member pm ON f.project_id = pm.project_id\r\n"
				+ "AND pm.member_id = :member_id";
		Map<String,Object> param = Map.of("member_id",member_id);
        return jdbcTemplate.query(sql, param,mapper);
    }
	public List<IndividualVO> indischedule(int member_id){
		String sql = "SELECT individualid,title,start_date FROM INDIVIDUAL_SCHEDULE WHERE member_id = :member_id";
		Map<String,Object> param = Map.of("member_id",member_id);
		return jdbcTemplate.query(sql, param,indimapper);
	}
	public int updateschedule(String date,String title) {
		String sql = "INSERT INTO individual_schedule values(1,:title,SEQ_INDIVIDUAL.NEXTVAL,TO_DATE(:date, 'YYYY-MM-DD HH24:MI'))";
		params.addValue("title", title);
		params.addValue("date", date);
		return jdbcTemplate.update(sql, params);
	}
	public int getIndividual() {
	    String sql = "SELECT SEQ_INDIVIDUAL.CURRVAL FROM DUAL";
	    return jdbcTemplate.queryForObject(sql,params,Integer.class);
	}
	public int deleteschedule(int individualID) {
		String sql = "DELETE FROM individual_schedule WHERE INDIVIDUALID = :individualID";
		params.addValue("individualID", individualID);
		return jdbcTemplate.update(sql, params);
	}
	
}
