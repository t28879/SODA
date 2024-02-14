package com.mysite.Soda.selectInvite;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SelectInviteDAO {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	RowMapper<SelectInviteVO> rowMapper = (rs, rowNum) -> 
		new SelectInviteVO(
				rs.getString("member_name"),
				rs.getString("company_name"), 
				rs.getString("profile_image"), 
				rs.getInt("member_id")
				);

	public List<SelectInviteVO> projectID(int project_id, int userNum) {
		// userNum을 이용해서 company_id를 가져온다
	    String companySql = "SELECT m.company_id FROM company c, member m WHERE c.company_id = m.company_id AND m.member_id = :userNum";
	    int companyId = jdbcTemplate.queryForObject(companySql, Map.of("userNum", userNum), Integer.class);

	    String sql = "SELECT m.name as member_name, c.name as company_name, m.profile_image, m.member_id " +
	            "FROM member m JOIN company c ON m.company_id = c.company_id " +
	            "LEFT JOIN project_member pm ON m.member_id = pm.member_id AND pm.project_id = :id " +
	            "WHERE c.company_id = :companyId AND pm.member_id IS NULL ORDER BY m.name ASC";

	    // 수정된 부분: NamedParameterJdbcTemplate를 사용하여 파라미터를 전달
	    Map<String, Object> params = Map.of("id", project_id, "companyId", companyId);
	    return jdbcTemplate.query(sql, params, rowMapper);
	}
}
