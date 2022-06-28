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
	(board_number NUMBER PRIMARY KEY, 
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
	-----------------------------------------
	//자유게시판 테이블
	CREATE TABLE bulletin
	(board_number NUMBER PRIMARY KEY,
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
	*/

}
