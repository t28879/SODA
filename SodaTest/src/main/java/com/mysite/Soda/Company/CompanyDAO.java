package com.mysite.Soda.Company;

import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CompanyDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	RowMapper<CompanyVO> rowmapper = (rs, rowNum) ->
		new CompanyVO(
				rs.getInt("company_id"),
				rs.getString("name"),
				rs.getString("logo"),
				rs.getString("url"),
				rs.getInt("wm"),
				rs.getString("ip"),
				rs.getInt("ipformem"),
				rs.getInt("loginip"),
				rs.getInt("logipformem")
				);
	
	public int cmpnewName(String name,int member_id) {
		String sql = "UPDATE company SET name = :name WHERE company_id = (SELECT company_id FROM member WHERE member_id = :member_id)";
		
        params.addValue("name", name);
        params.addValue("member_id", member_id);
        return jdbcTemplate.update(sql, params);
    }
	public int cmpAdminUpdate(int member_id,String oldname,String newname) {
		String sql = "INSERT INTO admin_change_log values(:member_id,'회사정보','회사명 변경','',:change,sysdate)";
		String change = oldname+" -> "+newname;
        params.addValue("member_id", member_id);
        params.addValue("change", change);
        return jdbcTemplate.update(sql, params);
    }
	public int cmpnewUrl(String Url,int member_id) {
		String sql = "UPDATE company SET url = :url WHERE company_id = (SELECT company_id FROM member WHERE member_id = :member_id)";
		
        params.addValue("url", Url);
        params.addValue("member_id", member_id);
        return jdbcTemplate.update(sql, params);
    }
	public int cmpAdminurlUpdate(int member_id,String oldurl,String newurl) {
		String sql = "INSERT INTO admin_change_log values(:member_id,'회사정보','전용URL 변경','',:change,sysdate)";
		String change = oldurl+" -> "+newurl;
        params.addValue("member_id", member_id);
        params.addValue("change", change);
        return jdbcTemplate.update(sql, params);
    }
	public CompanyVO getCompanyinfo(int member_id) {
		String sql1 = "SELECT * from company WHERE company_id = (SELECT company_id FROM member WHERE member_id = :member_id)";
		Map<String,Object> param = Map.of("member_id",member_id);
		return jdbcTemplate.queryForObject(sql1, param,rowmapper);
		
	}
	
}