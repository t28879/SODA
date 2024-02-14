package com.mysite.Soda.crud;

import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UpdateMemoDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    MapSqlParameterSource params = new MapSqlParameterSource();

    public int updateMemo(int member_id, String memo) {
        String sql = "UPDATE member SET memo = :memo WHERE member_id = :member_id";
        Map<String, Object> param = Map.of("memo", memo, "member_id", member_id);
        return jdbcTemplate.update(sql, param);
    }
}