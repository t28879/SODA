package com.mysite.Soda.CompanyMember;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CompanyMemberDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	RowMapper<CompanyMemberVO> rowMapper = (rs, rowNum) ->
		new CompanyMemberVO(
				rs.getInt("member_id"),
				rs.getString("name"),
				rs.getString("deptname"),
				rs.getString("job_name"),
				rs.getString("email"),
				rs.getString("phone_num"),
				rs.getTimestamp("join_date").toLocalDateTime(),
				rs.getInt("usage_status"),
				rs.getInt("company_manager"));
		
	
	public List<CompanyMemberVO> getallcmpmem(int member_id){
		String sql = "SELECT m.member_id,m.name,dept.name as deptname,m.job_name,m.email,m.phone_num,m.join_date,m.usage_status,m.company_manager\r\n"
				+ "FROM member m join departments dept ON m.department_id = dept.department_id AND m.company_id = (SELECT company_id FROM member WHERE member_id = :member_id)";
		Map<String,Object> param = Map.of("member_id",member_id);
		return jdbcTemplate.query(sql,param,rowMapper);
	}
	
	public int modifymember(String email,String name,String job_name,String phone_num) {
		String sql = "UPDATE member SET name = :name,job_name = :job_name,phone_num = :phone_num "+
					 " WHERE email = :email";
        params.addValue("name", name);
        params.addValue("job_name", job_name);
        params.addValue("phone_num", phone_num);
        params.addValue("email", email);
        return jdbcTemplate.update(sql, params);
    }
	public int modifydept(String email,String name) {
		String sql = "UPDATE departments SET name = :name WHERE department_id = (SELECT department_id FROM member WHERE email = :email)";
		params.addValue("name", name);
        params.addValue("email", email);
        return jdbcTemplate.update(sql, params);
    }
	public int banorre(int member_id,int USAGE_STATUS) {
		String sql = "UPDATE member SET USAGE_STATUS = :USAGE_STATUS WHERE member_id = :member_id";
		params.addValue("member_id", member_id);
        params.addValue("USAGE_STATUS", USAGE_STATUS);
        return jdbcTemplate.update(sql, params);
    }
	public int AdminBanLog(int member_id,String full,String BanOrRe) {
		String sql = "INSERT INTO admin_change_log values(:member_id,'사용자 관리','사용자 수정(상태)',:full,:BanOrRe,sysdate)";
		params.addValue("member_id", member_id);
		params.addValue("full", full);
        params.addValue("BanOrRe", BanOrRe);
        return jdbcTemplate.update(sql, params);
        
    }
	public int AdminmanagerChangeLog(int member_id,String full,String BanOrRe) {
		String sql = "INSERT INTO admin_change_log values(:member_id,'사용자 관리','사용자 수정(관리자 권한)',:full,:BanOrRe,sysdate)";
		params.addValue("member_id", member_id);
		params.addValue("full", full);
        params.addValue("BanOrRe", BanOrRe);
        return jdbcTemplate.update(sql, params);
    }
	public int NameChangeLog(int member_id,String full,String change) {
		String sql = "INSERT INTO admin_change_log values(:member_id,'사용자 관리','사용자 수정(이름)',:full,:change,sysdate)";
		params.addValue("member_id", member_id);
		params.addValue("full", full);
        params.addValue("change", change);
        return jdbcTemplate.update(sql, params);
    }
	public int JobChangeLog(int member_id,String full,String change) {
		String sql = "INSERT INTO admin_change_log values(:member_id,'사용자 관리','사용자 수정(직책)',:full,:change,sysdate)";
		params.addValue("member_id", member_id);
		params.addValue("full", full);
        params.addValue("change", change);
        return jdbcTemplate.update(sql, params);
    }
	public int PhonenumChangeLog(int member_id,String full,String change) {
		String sql = "INSERT INTO admin_change_log values(:member_id,'사용자 관리','사용자 수정(휴대폰번호)',:full,:change,sysdate)";
		params.addValue("member_id", member_id);
		params.addValue("full", full);
        params.addValue("change", change);
        return jdbcTemplate.update(sql, params);
    }
	public int DeptChangeLog(int member_id,String full,String change) {
		String sql = "INSERT INTO admin_change_log values(:member_id,'사용자 관리','사용자 수정(부서명)',:full,:change,sysdate)";
		params.addValue("member_id", member_id);
		params.addValue("full", full);
        params.addValue("change", change);
        return jdbcTemplate.update(sql, params);
    }
	public int modifymanager(int member_id,int COMPANY_MANAGER) {
		String sql = "UPDATE member SET COMPANY_MANAGER = :COMPANY_MANAGER WHERE member_id = :member_id";
		params.addValue("member_id", member_id);
        params.addValue("COMPANY_MANAGER", COMPANY_MANAGER);
        return jdbcTemplate.update(sql, params);
    }
}
