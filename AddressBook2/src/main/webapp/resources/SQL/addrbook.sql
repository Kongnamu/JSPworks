-- addrbook table 생성
CREATE TABLE addrbook(
    bnum        NUMBER PRIMARY KEY,
    username    VARCHAR2(20) NOT NULL,
    tel         VARCHAR2(20),
    email       VARCHAR2(30) UNIQUE, -- UNIQUE : 중복 여부 확인
    gender      VARCHAR2(6),
    regdate     TIMESTAMP DEFAULT SYSTIMESTAMP
);

CREATE SEQUENCE seq_bnum NOCACHE; -- 자동 순번

INSERT INTO addrbook(bnum, username, tel, email, gender)
VALUES (seq_bnum.NEXTVAL, '김정수', '010-3700-2092', 'test@test.com', '남');

-- 이메일 중복 체크
INSERT INTO addrbook(bnum, username, tel, email, gender)
VALUES (seq_bnum.NEXTVAL, '김정수', '010-3700-2092', 'test@test.com', '남');

COMMIT;

SELECT * FROM addrbook;

-- 이메일이 ziny@namver.com 찾기
SELECT email FROM addrbook
WHERE email = 'ziny@namver.com';

-- 하나 삭제
DELETE FROM addrbook WHERE bnum = 4;

DROP TABLE addrbook;
DROP SEQUENCE seq_bnum;