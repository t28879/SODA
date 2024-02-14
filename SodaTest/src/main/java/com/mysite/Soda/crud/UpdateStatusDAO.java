package com.mysite.Soda.crud;

import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UpdateStatusDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    MapSqlParameterSource params = new MapSqlParameterSource();

    public int updateStatus(int member_id, String statusValue) {
        String sql = "UPDATE member SET status_msg = :statusValue WHERE member_id = :member_id";
        Map<String, Object> param = Map.of("statusValue", statusValue, "member_id", member_id);
        return jdbcTemplate.update(sql, param);
    }
}
