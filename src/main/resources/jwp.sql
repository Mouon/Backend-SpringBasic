DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS (
                       userId          varchar(12)		NOT NULL,
                       password		varchar(12)		NOT NULL,
                       name			varchar(20)		NOT NULL,
                       email			varchar(50),

                       PRIMARY KEY               (userId)
);

INSERT INTO USERS VALUES('kuit', 'kuit', 'kuit', 'kuit@kuit.kr');

DROP TABLE IF EXISTS QUESTIONS;

CREATE TABLE QUESTIONS (
                           questionId 			bigint				auto_increment,
                           writer				varchar(30)			NOT NULL,
                           title				varchar(50)			NOT NULL,
                           contents			varchar(5000)		NOT NULL,
                           createdDate			timestamp			NOT NULL,
                           countOfAnswer int,
                           PRIMARY KEY               (questionId)
);

DROP TABLE IF EXISTS ANSWERS;

CREATE TABLE ANSWERS (
                         answerId 			bigint				auto_increment,
                         writer				varchar(30)			NOT NULL,
                         contents			varchar(5000)		NOT NULL,
                         createdDate			timestamp			NOT NULL,
                         questionId			bigint				NOT NULL,
                         PRIMARY KEY         (answerId)
);

INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES
    ('민병욱',
     '서버 질문 다 받습니다',
     '제가 바로 건대 병욱 MIN입니다 들어오시죠',
     CURRENT_TIMESTAMP(), 1);

INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES
    ('정경은',
     '같이 술 마실 분?',
     '제가 술을 참 좋아해요! 낯을 안가려서 어느 자리를 가도 mc 하는 편입니다 ^^ 저랑 술드실분?',
     CURRENT_TIMESTAMP(), 2);

INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES
    ('강연주',
     '스프링 질문 받아요',
     '모르셨죠? 사실 제가 스프링 왕입니다^^ 언제든 질문주세요.',
     CURRENT_TIMESTAMP(), 1);

INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES
    ('강연주',
     '저는 술 한번 시작하면 안멈추는 불도저에요^^',
     CURRENT_TIMESTAMP(), 2);

INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES
    ('임윤섭',
     '저 불렀나요?',
     CURRENT_TIMESTAMP(), 2);

INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES
    ('유가은',
     '춤 잘 추시는 분?',
     '내가 이 구역 춤신춤왕인데?',
     CURRENT_TIMESTAMP(), 0);

INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES
    ('정현석',
     '저는 안드 질문 받아요 24시 open',
     CURRENT_TIMESTAMP(), 1);

INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES
    ('강지윤',
     '이게 회장??!',
     CURRENT_TIMESTAMP(), 3);