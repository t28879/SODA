package com.mysite.Soda.IP;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysite.Soda.MemberVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class IPDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	RowMapper<IPafterVO> forcmp = (rs,rowNum) ->
	new IPafterVO(
			rs.getString("ipaddress"),
			rs.getInt("company_id"),
			rs.getTimestamp("up_date").toLocalDateTime(),
			rs.getString("email"));
	RowMapper<TestVO> testvo = (rs,rowNum) ->
	new TestVO(
			rs.getString("ipaddress")
			);
	RowMapper<MemberVO> rowmapformem = (rs,rowNum) ->
	new MemberVO(
			rs.getInt("member_id"),
			rs.getString("name"),
			rs.getString("deptname"),
			rs.getString("jobname"),
			rs.getString("email"),
			rs.getInt("company_id"),
			rs.getString("phone_num"),null);
	
	RowMapper<IPVO> rowMapper = (rs,rowNum) ->
		new IPVO(
				rs.getInt("IP"),
				rs.getString("IPaddress"),
				rs.getString("text"),
				rs.getTimestamp("up_date").toLocalDateTime(),
				rs.getString("email"),
				rs.getInt("company_id"));
		
		RowMapper<IPMemberVO> IPmemrowMapper = (rs,rowNum) ->
		new IPMemberVO(
				rs.getInt("member_id"),
				rs.getInt("ipformem"),
				rs.getString("name"),
				rs.getString("email"),
				rs.getTimestamp("allow_date").toLocalDateTime(),
				rs.getInt("company_id"));

	public List<IPVO> getIPinfo(int member_id) {
		String sql = "SELECT c.company_id,c.ip,DI.ipaddress,DI.text,DI.up_date,m.email\r\n"
				+ "FROM company c \r\n"
				+ "JOIN download_ip DI ON c.company_id = DI.company_id AND c.company_id = (SELECT company_id FROM member WHERE member_id = :member_id)\r\n"
				+ "JOIN member m ON di.member_id = m.member_id";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.query(sql,params,rowMapper);
	}
	public List<IPVO> getIPlogininfo(int member_id) {
		String sql = "SELECT c.company_id,c.loginip as ip,DI.ip as ipaddress,DI.text,DI.up_date,m.email\r\n"
				+ "FROM company c \r\n"
				+ "JOIN login_ip DI ON c.company_id = DI.company_id AND c.company_id = (SELECT company_id FROM member WHERE member_id = :member_id)\r\n"
				+ "JOIN member m ON di.member_id = m.member_id";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.query(sql,params,rowMapper);
	}
	public List<IPMemberVO> getIPmemberinfo(int member_id) {
		String sql = "SELECT c.company_id,c.ipformem,m.member_id,m.name,m.email,da.allow_date\r\n"
				+ "FROM company c \r\n"
				+ "JOIN download_allow da ON c.company_id = da.company_id AND c.company_id = (SELECT company_id FROM member WHERE member_id = :member_id)\r\n"
				+ "JOIN member m ON da.allow_id = m.member_id";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.query(sql,params,IPmemrowMapper);
	}
	public List<IPMemberVO> getIPloginmemberinfo(int member_id) {
		String sql = "SELECT c.company_id,c.LOGIPFORMEM as ipformem,m.member_id,m.name,m.email,da.allow_date\r\n"
				+ "FROM company c \r\n"
				+ "JOIN login_allow da ON c.company_id = da.company_id AND c.company_id = (SELECT company_id FROM member WHERE member_id = :member_id)\r\n"
				+ "JOIN member m ON da.allow_id = m.member_id";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.query(sql,params,IPmemrowMapper);
	}
	public IPafterVO getAfterupdate(int member_id,String ipaddress) {
		String query = "SELECT di.IPADDRESS,di.company_id,up_date,m.email\r\n"
				+ "FROM download_ip di JOIN member m ON di.member_id = m.member_id AND di.company_id = (SELECT company_id FROM member WHERE member_id = :member_id)\r\n"
				+ "AND di.ipaddress = :ipaddress";
		String resultString = insertDots(ipaddress);
		
		params.addValue("member_id", member_id);
		params.addValue("ipaddress", resultString);
		return jdbcTemplate.queryForObject(query, params,forcmp);
	}
	public IPafterVO getAfterloginipupdate(int member_id,String ipaddress) {
		String query = "SELECT di.IP as ipaddress,di.company_id,up_date,m.email\r\n"
				+ "FROM login_ip di JOIN member m ON di.member_id = m.member_id AND di.company_id = (SELECT company_id FROM member WHERE member_id = :member_id)\r\n"
				+ "AND di.ip = :ipaddress";
		String resultString = insertDots(ipaddress);
		
		params.addValue("member_id", member_id);
		params.addValue("ipaddress", resultString);
		return jdbcTemplate.queryForObject(query, params,forcmp);
	}
	public boolean selectipaddress(int member_id,String ipaddress) {
		String query = "SELECT IPADDRESS FROM DOWNLOAD_IP WHERE ipaddress = :ipaddress AND company_id = (SELECT company_id FROM member WHERE member_id = :member_id)";
		String resultString = insertDots(ipaddress);
		params.addValue("member_id", member_id);
		params.addValue("ipaddress", resultString);
		try {
	        jdbcTemplate.queryForObject(query, params, String.class);
	        return true; 
	    } catch (EmptyResultDataAccessException e) {
	        return false; 
	    } catch (IncorrectResultSizeDataAccessException e) {
	        return false;
	    } catch (DataAccessException e) {
	        return false;
	    }
	}
	public boolean selectloginipaddress(int member_id,String ipaddress) {
		String query = "SELECT ip as IPADDRESS FROM login_IP WHERE ip = :ipaddress AND company_id = (SELECT company_id FROM member WHERE member_id = :member_id)";
		String resultString = insertDots(ipaddress);
		params.addValue("member_id", member_id);
		params.addValue("ipaddress", resultString);
		try {
	        jdbcTemplate.queryForObject(query, params, String.class);
	        return true; 
	    } catch (EmptyResultDataAccessException e) {
	        return false; 
	    } catch (IncorrectResultSizeDataAccessException e) {
	        return false;
	    } catch (DataAccessException e) {
	        return false;
	    }
	}
	public void updateIPaddress(int member_id,String ipaddress,String ipexplain) {
		String sql = "INSERT INTO DOWNLOAD_IP VALUES(:ipaddress,(SELECT company_id FROM member WHERE member_id = :member_id),:ipexplain,:member_id,sysdate)";
		String resultString = insertDots(ipaddress);
		
		params.addValue("member_id", member_id);
		params.addValue("ipaddress", resultString);
		params.addValue("ipexplain", ipexplain);
		jdbcTemplate.update(sql, params);
	}
	public void updateloginIPaddress(int member_id,String ipaddress,String ipexplain) {
		String sql = "INSERT INTO login_IP VALUES(:ipaddress,(SELECT company_id FROM member WHERE member_id = :member_id),:ipexplain,:member_id,sysdate)";
		String resultString = insertDots(ipaddress);
		
		params.addValue("member_id", member_id);
		params.addValue("ipaddress", resultString);
		params.addValue("ipexplain", ipexplain);
		jdbcTemplate.update(sql, params);
	}
	public void updateDownloadIPonoff(int member_id,int onoff) {
		String sql = "UPDATE company SET IP = :onoff WHERE company_id = (SELECT company_id FROM member WHERE member_id = :member_id)";
		
		params.addValue("member_id", member_id);
		params.addValue("onoff", onoff);
		jdbcTemplate.update(sql, params);
	}
	public void updateLoginIPonoff(int member_id,int onoff) {
		String sql = "UPDATE company SET LOGINIP = :onoff WHERE company_id = (SELECT company_id FROM member WHERE member_id = :member_id)";
		
		params.addValue("member_id", member_id);
		params.addValue("onoff", onoff);
		jdbcTemplate.update(sql, params);
	}
	public void updateDownloadIPMemberonoff(int member_id,int onoff) {
		String sql = "UPDATE company SET IPFORMEM = :onoff WHERE company_id = (SELECT company_id FROM member WHERE member_id = :member_id)";
		
		params.addValue("member_id", member_id);
		params.addValue("onoff", onoff);
		jdbcTemplate.update(sql, params);
	}
	public void updateLoginIPMemberonoff(int member_id,int onoff) {
		String sql = "UPDATE company SET LOGIPFORMEM = :onoff WHERE company_id = (SELECT company_id FROM member WHERE member_id = :member_id)";
		
		params.addValue("member_id", member_id);
		params.addValue("onoff", onoff);
		jdbcTemplate.update(sql, params);
	}
	public List<MemberVO> getMemberInfo(int member_id){
		String sql = "SELECT m.company_id,m.member_id,m.name,m.job_name as jobname,d.name as deptname,m.email,m.phone_num\r\n"
				+ "FROM member m \r\n"
				+ "JOIN departments d ON m.department_id = d.department_id AND m.company_id = (SELECT company_id FROM member WHERE member_id = :member_id)";
		Map<String,Object> params = Map.of("member_id",member_id);
		return jdbcTemplate.query(sql,params,rowmapformem);
	}
	public int updateIPmember(int member_id,Long ipmember_id) {
		String sql = "INSERT INTO DOWNLOAD_ALLOW VALUES((SELECT company_id FROM member WHERE member_id = :member_id),:ipmember_id,sysdate,:member_id)";
		params.addValue("member_id", member_id);
		params.addValue("ipmember_id", ipmember_id);
		return jdbcTemplate.update(sql, params);
	}
	public int updateloginIPmember(int member_id,Long ipmember_id) {
		String sql = "INSERT INTO LOGIN_ALLOW VALUES((SELECT company_id FROM member WHERE member_id = :member_id),:ipmember_id,sysdate,:member_id)";
		params.addValue("member_id", member_id);
		params.addValue("ipmember_id", ipmember_id);
		return jdbcTemplate.update(sql, params);
	}
	public int deleteIPaddress(int company_id, String ipaddress) {
		String sql = "DELETE FROM download_ip WHERE company_id = :company_id AND ipaddress = :ipaddress";
		params.addValue("company_id", company_id);
		params.addValue("ipaddress", ipaddress);
		return jdbcTemplate.update(sql, params);
	}
	public int deleteLoginIPaddress(int company_id, String ipaddress) {
		String sql = "DELETE FROM login_ip WHERE company_id = :company_id AND ip = :ipaddress";
		params.addValue("company_id", company_id);
		params.addValue("ipaddress", ipaddress);
		return jdbcTemplate.update(sql, params);
	}
	public int deleteIPmember(int company_id, String email) {
		String sql = "DELETE FROM download_allow WHERE company_id = :company_id AND allow_id = (SELECT member_id FROM member WHERE email = :email)";
		params.addValue("company_id", company_id);
		params.addValue("email", email);
		return jdbcTemplate.update(sql, params);
	}
	public int deleteLoginIPmember(int company_id, String email) {
		String sql = "DELETE FROM login_allow WHERE company_id = :company_id AND allow_id = (SELECT member_id FROM member WHERE email = :email)";
		params.addValue("company_id", company_id);
		params.addValue("email", email);
		return jdbcTemplate.update(sql, params);
	}
	public int AdminChangeLog(int member_id,String target1,String target2,String object,String change) {
		String sql = "INSERT INTO admin_change_log values(:member_id,:target1,:target2,:object,:change,sysdate)";
		params.addValue("member_id", member_id);
		params.addValue("target1", target1);
		params.addValue("target2", target2);
        params.addValue("object", object);
        params.addValue("change", change);
        return jdbcTemplate.update(sql, params);
    }
	private static String insertDots(String original) {
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < original.length(); i++) {
            resultBuilder.append(original.charAt(i));
            if ((i + 1) % 3 == 0 && (i + 1) < original.length()) {
                resultBuilder.append('.');
            }
        }
        return resultBuilder.toString();
    }
}