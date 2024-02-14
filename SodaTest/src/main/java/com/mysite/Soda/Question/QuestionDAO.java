package com.mysite.Soda.Question;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QuestionDAO {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();

	RowMapper<QuestionVO> rowmapper = (rs, rowNum) -> 
	new QuestionVO(
			rs.getInt("question_id"), 
			rs.getString("subject"),
			rs.getString("content"), 
			rs.getTimestamp("createdate").toLocalDateTime(), 
			rs.getInt("company_id"),
			rs.getString("name"),
			rs.getString("email"),
			rs.getInt("member_id"),
			rs.getDate("MODIFICATION_DATE"),
			null
			);
	

	public List<QuestionVO> getquestionlist(int member_id,int pagenum) {
		int questionendlengh = pagenum*20;
		int questionstartlength = pagenum*20 - 19;
		String sql = "SELECT *\r\n"
				+ "FROM (\r\n"
				+ "SELECT q.*, ROWNUM AS rn\r\n"
				+ "FROM (\r\n"
				+ "SELECT * \r\n"
				+ "FROM question qu\r\n"
				+ "JOIN member m ON qu.member_id = m.member_id AND qu.company_id = (SELECT company_id FROM member WHERE member_id = :member_id)\r\n"
				+ "ORDER BY qu.createdate desc\r\n"
				+ ") q\r\n"
				+ ")\r\n"
				+ "WHERE rn BETWEEN :questionstartlength AND :questionendlengh";
		params.addValue("member_id", member_id);
		params.addValue("questionstartlength", questionstartlength);
		params.addValue("questionendlengh", questionendlengh);
		return jdbcTemplate.query(sql, params, rowmapper);
	}
	public int getquestionlistsize(int member_id) {
		String sql = "SELECT count(*) FROM question WHERE company_id = (SELECT company_id FROM member WHERE member_id = :member_id)";
		Map<String, Object> params = Map.of("member_id", member_id);
		return jdbcTemplate.queryForObject(sql, params,Integer.class);
	}

	public QuestionVO getquestion(int question_id) {
		String sql = "SELECT * \r\n"
				+ "FROM question q\r\n"
				+ "JOIN member m ON q.member_id = m.member_id AND q.question_id = :question_id";
		Map<String, Object> params = Map.of("question_id", question_id);
		return jdbcTemplate.queryForObject(sql, params, rowmapper);
	}

	public boolean checkquestionlist(int member_id) {
		String sql = "SELECT * \r\n"
				+ "FROM question q\r\n"
				+ "JOIN member m ON q.member_id = m.member_id AND  q.company_id = (SELECT company_id FROM member WHERE member_id = :member_id)";
		Map<String, Object> params = Map.of("member_id", member_id);
		try {
			jdbcTemplate.query(sql, params, rowmapper);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		} catch (IncorrectResultSizeDataAccessException e) {
			return false;
		} catch (DataAccessException e) {
			return false;
		}
	}

	public int createquestion(String subject, String content, int member_id) {
		String sql = "INSERT INTO question VALUES(seq_question.nextval,:subject,:content,sysdate,(SELECT company_id from member WHERE member_id = :member_id),:member_id,sysdate)";
		params.addValue("subject", subject);
		params.addValue("content", content);
		params.addValue("member_id", member_id);
		return jdbcTemplate.update(sql,params);
	}
	public int modifyquestion(String content, int question_id) {
		String sql = "UPDATE question SET content = :content,MODIFICATION_DATE = sysdate WHERE question_id = :question_id";
		params.addValue("content", content);
		params.addValue("question_id", question_id);
		return jdbcTemplate.update(sql,params);
	}
	public int deletequestion(int question_id) {
		String sql = "DELETE FROM question WHERE question_id = :question_id";
		params.addValue("question_id", question_id);
		return jdbcTemplate.update(sql,params);
	}

}