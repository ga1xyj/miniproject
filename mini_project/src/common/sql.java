package common;

public class sql {
	/*
	//멤버 테이블 
	CREATE TABLE members
	(name VARCHAR2(100) NOT NULL,
	student_number NUMBER(20) NOT NULL UNIQUE,
	department_name VARCHAR2(50) NOT NULL, 
	id VARCHAR2(50) PRIMARY KEY,
	pwd VARCHAR2(50) NOT NULL,
	role NUMBER(2) DEFAULT 0);
	-----------------------------------------
	//공지 테이블
	CREATE TABLE notice
	(b_number NUMBER PRIMARY KEY,
    board_number NUMBER NOT NULL UNIQUE,
	title VARCHAR2(100) NOT NULL,
	content VARCHAR2(2000) NOT NULL,
	board_date DATE DEFAULT sysdate
	);

	//공지 시퀀스 설정
	CREATE SEQUENCE notice_seq
	START WITH 1
	INCREMENT BY 1
	NOCYCLE
	NOCACHE;
	
	//공지 데이터
	INSERT INTO notice (b_number, board_number, title, content) VALUES (notice_seq.nextval, (SELECT NVL(MAX(board_number)+1,1) FROM notice),
'2022학년도 2학기 복학 및 휴학 신청 안내', '2022학년도 1학기 복학 및 휴학을 아래와 같이 안내합니다.');
INSERT INTO notice (b_number, board_number, title, content) VALUES (notice_seq.nextval, (SELECT NVL(MAX(board_number)+1,1) FROM notice),
'2022학년도 여름계절수업 개설 수업시간표 안내', '2022학년도 여름계절수업 개설 수업시간표를 붙임과 같이 알려드립니다.');
	-----------------------------------------
	//자유게시판 테이블
	CREATE TABLE bulletin
	(b_number NUMBER PRIMARY KEY,
    board_number NUMBER NOT NULL UNIQUE,
    id VARCHAR2(50) NOT NULL,
	title VARCHAR2(100) NOT NULL,
	content VARCHAR2(2000) NOT NULL,
	board_date DATE DEFAULT sysdate,
    FOREIGN KEY(id)
    REFERENCES members(id)
	);
	
	//자유게시판 시퀀스 설정
	CREATE SEQUENCE bb_seq
	START WITH 1
	INCREMENT BY 1
	NOCYCLE
	NOCACHE;
	
	//자유게시판 데이터
	INSERT INTO bulletin (b_number, board_number, id, title, content) VALUES (bb_seq.nextval, (SELECT NVL(MAX(board_number)+1,1) FROM bulletin), 'admin', 'istp 질문받음', '무물');
	INSERT INTO bulletin (b_number, board_number, id, title, content) VALUES (bb_seq.nextval, (SELECT NVL(MAX(board_number)+1,1) FROM bulletin), 'test', '카공카페', '추천좀');
	-----------------------------------------
	//자유게시판댓글 테이블
	CREATE TABLE bulletin_comment
	(bc_number NUMBER PRIMARY KEY,
    comment_number NUMBER,
    board_number NUMBER,
    id VARCHAR2(50) NOT NULL,
	content VARCHAR2(2000) NOT NULL,
	board_date DATE DEFAULT sysdate,
    FOREIGN KEY(id)
    REFERENCES members(id),
     FOREIGN KEY(board_number)
    REFERENCES bulletin(board_number)
	);
	
	//자유게시판댓글 시퀀스
	CREATE SEQUENCE bc_seq
	START WITH 1
	INCREMENT BY 1
	NOCYCLE
	NOCACHE;
	
	//댓글 데이터
	INSERT INTO bulletin_comment (bc_number, board_number, id, content) VALUES (bc_seq.nextval, 1, 'test', '나도 istp임');
	INSERT INTO bulletin_comment (bc_number, board_number, id, content) VALUES (bc_seq.nextval, 1, 'test', '저메추 ㄱㄱ');
	INSERT INTO bulletin_comment (bc_number, board_number, id, content) VALUES (bc_seq.nextval, 2, 'admin', '스타벅스');
	-----------------------------------------1
	//관리자 계정 생성
	INSERT INTO members
   (name, student_number, department_name, id, pwd, role)
   VALUES ('관리자', 1, '관리자', 'admin', 'admin', 1);
   
   //테스트 계정 생성
    INSERT INTO members
   (name, student_number, department_name, id, pwd)
   VALUES ('주은하', 2016115325, '컴공', 'test', 'test');
    */
	
	
	

}
