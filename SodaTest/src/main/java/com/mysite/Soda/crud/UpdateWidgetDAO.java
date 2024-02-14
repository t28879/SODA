package com.mysite.Soda.crud;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UpdateWidgetDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public int updateWidget(int member_id, String[] orderArray) {
        String sql = "UPDATE widget SET order_column = :order_column WHERE widget_name = :widget_name AND member_id = :member_id";

        for (int i = 0; i < orderArray.length; i++) {
            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("order_column", i)
                    .addValue("widget_name", orderArray[i].trim())
                    .addValue("member_id", member_id);

           jdbcTemplate.update(sql, params);
        }

        return 0;
    }
}
