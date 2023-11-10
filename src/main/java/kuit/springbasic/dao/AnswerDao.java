package kuit.springbasic.dao;

import kuit.springbasic.domain.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnswerDao {

    private final JdbcTemplate jdbcTemplate;

    public Answer insert(Answer answer) throws SQLException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into answers (writer, contents, createdDate, questionId) values (?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, answer.getWriter());
            ps.setString(2, answer.getContents());
            ps.setDate(3, answer.getCreatedDate());
            return ps;
        }, keyHolder);
        answer.setAnswerId(keyHolder.getKey().longValue());
        return answer;
    }

    public List<Answer> findAllByQuestionId(Long questionId) {
        String sql = "select * from answers where questionId=? order by answerId";
        return jdbcTemplate.query(sql, answerRowMapper(), questionId);
    }

    private RowMapper<Answer> answerRowMapper() {
        return (rs, rowNum) -> {
            Answer answer = new Answer();
            answer.setAnswerId(rs.getLong("answerId"));
            answer.setWriter(rs.getString("writer"));
            answer.setContents(rs.getString("contents"));
            answer.setCreatedDate(rs.getDate("createdDate"));
            answer.setQuestionId(rs.getLong("questionId"));
            return answer;
        };
    }
}
