package com.mysite.Soda.crud;

import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UpdateBackgroundDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    MapSqlParameterSource params = new MapSqlParameterSource();

    public int updateBackground(int member_id, String imagePath) {
        String sql = "UPDATE all_setting SET backgroundimg = :imagePath where member_id = :member_id";
        Map<String, Object> param = Map.of("imagePath", imagePath, "member_id", member_id);
        return jdbcTemplate.update(sql, param);
    }
}
