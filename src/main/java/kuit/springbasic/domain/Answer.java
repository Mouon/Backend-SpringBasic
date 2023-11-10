package kuit.springbasic.domain;


import lombok.*;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Answer {
    private Long answerId;
    private Long questionId;
    private String writer;
    private String contents;
    private Date createdDate;

    public Answer(Long questionId, String writer, String contents) {
        this.questionId = questionId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = Date.valueOf(LocalDate.now());
    }

}
