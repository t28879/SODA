package com.mysite.Soda.crud;

import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class InsertFolderNameDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    MapSqlParameterSource params = new MapSqlParameterSource();

    public int insertFolderName(int member_id, String folderName) {
        String sql = "INSERT INTO project_folder_name (name, folder_id, member_id, recent_date) VALUES (:folderName, folder_name.NEXTVAL, :member_id, sysdate)";
        Map<String, Object> param = Map.of("folderName", folderName, "member_id", member_id);
        return jdbcTemplate.update(sql, param);
    }
}
