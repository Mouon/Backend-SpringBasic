package kuit.springbasic.dao;

import kuit.springbasic.domain.Question;
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
public class QuestionDao {

    private final JdbcTemplate jdbcTemplate;

    public Question insert(Question question) throws SQLException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into questions (writer, title, contents, createdDate) values (?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, question.getWriter());
            ps.setString(2, question.getTitle());
            ps.setString(3, question.getContents());
            ps.setObject(4, question.getCreatedDate());
            return ps;
        }, keyHolder);
        return findByQuestionId(keyHolder.getKey().longValue());
    }

    public void update(Question question) {
        String sql = "update questions set title = ?, contents = ? where questionId = ?";
        jdbcTemplate.update(sql, question.getTitle(), question.getContents(), question.getQuestionId());
    }

    public void delete(int id) {
        String sql = "delete from questions where questionId=?";
        jdbcTemplate.update(sql, pstmt -> pstmt.setObject(1, id));
    }

    public List<Question> findAll() {
        String sql = "select * from questions order by questionId";
        return jdbcTemplate.query(sql,questionRowMapper());
    }

    public Question findByQuestionId(Long questionId) throws SQLException {
        String sql = "select questionId, writer, title, contents, createdDate, countOfAnswer from questions where questionId=?";
        return jdbcTemplate.queryForObject(sql,questionRowMapper(), questionId);
    }

    public void updateCountOfAnswer(Question question) {
        String sql = "update questions set countOfAnswer=? where questionId=?";
        jdbcTemplate.update(sql,
                pstmt -> {
                    pstmt.setObject(1, question.getCountOfAnswer());
                    pstmt.setObject(2, question.getQuestionId());
                });
    }

    private RowMapper<Question> questionRowMapper() {
        return (rs, rowNum) -> {
            Question question = new Question();
            question.setQuestionId(rs.getLong("questionId"));
            question.setWriter(rs.getString("writer"));
            question.setTitle(rs.getString("title"));
            question.setContents(rs.getString("contents"));
            question.setCreatedDate(rs.getDate("createdDate"));
            question.setCountOfAnswer(rs.getLong("countOfAnswer"));
            return question;
        };
    }

}
