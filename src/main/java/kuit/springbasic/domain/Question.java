package kuit.springbasic.domain;


import java.sql.Date;
import java.time.LocalDate;
import lombok.*;

//어노테이션으로 대체
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(exclude = {"questionId"})
public class Question {
    private Long questionId;
    private String writer;
    private String title;
    private String contents;
    private Date createdDate;
    private Long countOfAnswer;

    // 새로운 질문 객체를 생성할 때 사용
    public Question(String writer, String title, String contents, Long countOfAnswer) {
        this.questionId = 0L;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = Date.valueOf(LocalDate.now());
        this.countOfAnswer = countOfAnswer;
    }

    // 질문의 제목과 내용을 업데이트
    public void updateTitleAndContents(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    // 답변 수를 증가시킴
    public void increaseCountOfAnswer() {
        countOfAnswer += 1;
    }

    // 답변 수를 감소시킴
    public void decreaseCountOfAnswer() {
        countOfAnswer -= 1;
    }

    // 주어진 사용자와 질문의 작성자가 동일한지 확인
    public boolean isSameUser(User user) {
        return writer.equals(user.getUserId());
    }

    // 답변 수를 업데이트
    public void updateCountofAnswer(Question question){
        this.countOfAnswer = question.countOfAnswer;
    }

    // 질문 객체를 업데이트
    public void update(Question question) {
        this.questionId = question.questionId;
        this.writer = question.writer;
        this.title = question.title;
        this.contents = question.contents;
        this.createdDate = question.createdDate;
        this.countOfAnswer = question.countOfAnswer;
    }


}
