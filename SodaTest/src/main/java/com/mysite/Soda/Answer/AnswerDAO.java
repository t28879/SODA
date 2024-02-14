package com.mysite.Soda.Answer;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysite.Soda.Question.QuestionVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AnswerDAO {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	RowMapper<AnswerVO> rowmapper = (rs,rowNum) ->
	new AnswerVO(
			rs.getInt("answer_id"),
			rs.getString("content"),
			rs.getDate("createdate"),
			rs.getInt("question_id"),
			rs.getInt("member_id"),
			rs.getString("name"),
			rs.getString("email"),
			rs.getDate("MODIFICATION_DATE")
			);

	
	public int createanswer(int question_id,String content,int member_id) {
		String sql = "INSERT INTO answer VALUES(SEQ_ANSWER.NEXTVAL,:content,sysdate,:question_id,:member_id,sysdate)";
		params.addValue("content", content);
		params.addValue("question_id", question_id);
		params.addValue("member_id", member_id);
		return jdbcTemplate.update(sql,params);
	}
	public List<AnswerVO> getanswerlist(int question_id){
		String sql = "SELECT * \r\n"
				+ "FROM answer a\r\n"
				+ "JOIN member m ON a.member_id = m.member_id AND a.question_id = :question_id";
		Map<String,Object> param = Map.of("question_id",question_id);
		try {
			jdbcTemplate.query(sql, param,rowmapper);
			return jdbcTemplate.query(sql, param,rowmapper);
	    } catch (EmptyResultDataAccessException e) {
	        return null; 
	    } catch (IncorrectResultSizeDataAccessException e) {
	        return null;
	    } catch (DataAccessException e) {
	        return null;
	    }
	}
	public int modifyanswer(int answer_id,String content) {
		String sql = "UPDATE answer SET content = :content,MODIFICATION_DATE = sysdate WHERE answer_id = :answer_id";
		params.addValue("content", content);
		params.addValue("answer_id", answer_id);
		return jdbcTemplate.update(sql,params);
	}
	public int getquestionID(int answer_id) {
		String sql = "SELECT question_id FROM answer WHERE answer_id = :answer_id";
		Map<String,Object> params = Map.of("answer_id",answer_id);
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}
	public int deleteanswer(int answer_id) {
		String sql = "DELETE FROM answer WHERE answer_id = :answer_id";
		Map<String,Object> params = Map.of("answer_id",answer_id);
		return jdbcTemplate.update(sql, params);
	}
	
	
	
}