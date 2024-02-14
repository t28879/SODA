package com.mysite.Soda.crud;

import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UpdateOkDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    MapSqlParameterSource params = new MapSqlParameterSource();

    public int ok(int member_id, int result) {
        String sql = "update member set ok = :result where member_id = :member_id";
        Map<String, Object> param = Map.of("result", result, "member_id", member_id);
        return jdbcTemplate.update(sql, param);
    }
}
