package com.mysite.Soda.gonothinking;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AllScheduleDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	public boolean checkIfAlreadyScheduled(int feed_id, int userNum) {
        String sql = "SELECT COUNT(*) FROM gonoschedule WHERE feed_id = :feed_id AND member_id = :userNum";
        params.addValue("feed_id", feed_id);
        params.addValue("userNum", userNum);
        int count = jdbcTemplate.queryForObject(sql, params, Integer.class);
        return count > 0;
    }

    public void updateGoColumn(int feed_id) {
        String sql = "UPDATE feed_schedule SET go = go + 1 WHERE feed_id = :feed_id";
        params.addValue("feed_id", feed_id);
        jdbcTemplate.update(sql, params);
    }

    public void updateNoColumn(int feed_id) {
        String sql = "UPDATE feed_schedule SET no = no + 1 WHERE feed_id = :feed_id";
        params.addValue("feed_id", feed_id);
        jdbcTemplate.update(sql, params);
    }

    public void updateThinkingColumn(int feed_id) {
        String sql = "UPDATE feed_schedule SET thinking = thinking + 1 WHERE feed_id = :feed_id";
        params.addValue("feed_id", feed_id);
        jdbcTemplate.update(sql, params);
    }
    
    public void updateGoColumnCancel(int feed_id) {
        // 이미 참석한 상태에서 다시 참석을 누른 경우, 'go' 값을 감소시킴
        String sql = "UPDATE feed_schedule SET go = go - 1 WHERE feed_id = :feed_id";
        params.addValue("feed_id", feed_id);
        jdbcTemplate.update(sql,params);
    }
    
    public void updateNoColumnCancel(int feed_id) {
        String sql = "UPDATE feed_schedule SET no = no - 1 WHERE feed_id = :feed_id";
        params.addValue("feed_id", feed_id);
        jdbcTemplate.update(sql, params);
    }
    
    public void updateThinkingColumnCancel(int feed_id) {
        String sql = "UPDATE feed_schedule SET thinking = thinking - 1 WHERE feed_id = :feed_id";
        params.addValue("feed_id", feed_id);
        jdbcTemplate.update(sql, params);
    }

    public void deleteSchedule(int feed_id, int userNum) {
        String sql = "DELETE FROM gonoschedule WHERE feed_id = :feed_id AND member_id = :userNum";
        params.addValue("feed_id", feed_id);
        params.addValue("userNum", userNum);
        jdbcTemplate.update(sql, params);
    }
    
    

    public void addOrUpdateSchedule(int feed_id, int userNum) {
        String sql = "MERGE INTO gonoschedule USING dual " +
                     "ON (feed_id = :feed_id AND member_id = :userNum) " +
                     "WHEN NOT MATCHED THEN " +
                     "  INSERT (feed_id, member_id) VALUES (:feed_id, :userNum)";
        params.addValue("feed_id", feed_id);
        params.addValue("userNum", userNum);
        jdbcTemplate.update(sql, params);
    }
}
