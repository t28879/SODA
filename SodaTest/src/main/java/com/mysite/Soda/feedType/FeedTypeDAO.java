package com.mysite.Soda.feedType;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FeedTypeDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    MapSqlParameterSource params = new MapSqlParameterSource();
    RowMapper<FeedTypeVO> rowmapper = (rs, rowNum) ->
            new FeedTypeVO(
            		rs.getInt("feed_id"),
            		rs.getInt("type")
            );

    public List<FeedTypeVO> getFeedType(int clickedId) {
        String sql = "SELECT feed_id,type FROM feed WHERE project_id = :clickedId";
        Map<String, Object> param = Map.of("clickedId", clickedId);
        return jdbcTemplate.query(sql, param, rowmapper);
    }
    public String getFeedTextType(int feed_id) {
        String sql = "SELECT title FROM feed_text WHERE feed_Id = :feed_id";
        Map<String, Object> param = Map.of("feed_id", feed_id);
        return jdbcTemplate.queryForObject(sql, param, String.class);
    }
    public String getFeedWorkType(int feed_id) {
        String sql = "SELECT title FROM feed_work WHERE feed_Id = :feed_id";
        Map<String, Object> param = Map.of("feed_id", feed_id);
        return jdbcTemplate.queryForObject(sql, param, String.class);
    }
    public String getFeedScheduleType(int feed_id) {
        String sql = "SELECT title FROM feed_schedule WHERE feed_Id = :feed_id";
        Map<String, Object> param = Map.of("feed_id", feed_id);
        return jdbcTemplate.queryForObject(sql, param, String.class);
    }
    public String getFeedTodoType(int feed_id) {
        String sql = "SELECT title FROM feed_todo WHERE feed_Id = :feed_id";
        Map<String, Object> param = Map.of("feed_id", feed_id);
        return jdbcTemplate.queryForObject(sql, param, String.class);
    }
}
