--board 테이블 생성
CREATE TABLE board(
    bno         NUMBER PRIMARY KEY,
    title       VARCHAR2(100) NOT NULL,
    content     CLOB NOT NULL,
    createdate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    modifydate  TIMESTAMP,
    hit         NUMBER DEFAULT 0,
    filename    VARCHAR2(50),
    id          VARCHAR2(20) NOT NULL,
    FOREIGN KEY (id) REFERENCES member (id) on DELETE CASCADE
);

CREATE SEQUENCE seq_bno NOCACHE;

INSERT INTO board (bno, title, content, id)
VALUES (seq_bno.NEXTVAL, '글제목', '글내용입니다.', 'khit');

COMMIT;
SELECT * FROM board;

-- 게시글 3번 삭제
DELETE FROM board WHERE bno = 4;

-- 글번호가 5번인 게시글의 제목을 '공지사항 수정', 내용을 '공지사항 수정입니다.'로 바꾸기
UPDATE board SET title = '공지사항 수정', content = '공지사항 수정입니다.'
WHERE bno = 5;

-- 글번호가 5번인 게시글의 조회수 1증가
UPDATE BOARD SET hit = hit + 1
WHERE bno = 5;