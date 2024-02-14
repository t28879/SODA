package com.mysite.Soda.chatting;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class InviteMemberDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	RowMapper<InviteMemberVO> rowmapper = (rs, rowNum) ->
		new InviteMemberVO(
				rs.getInt("member_id"),
				rs.getString("name")
				);
		
		public List<InviteMemberVO> inviteMember(String[] selectedItems) {
		    String sql = "SELECT member_id, name FROM member WHERE member_id IN (:selectedItems)";
		    Map<String, Object> paramMap = Collections.singletonMap("selectedItems", Arrays.asList(selectedItems));
		    return jdbcTemplate.query(sql, paramMap, rowmapper);
		}
}
