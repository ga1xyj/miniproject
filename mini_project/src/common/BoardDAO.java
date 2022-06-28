package common;

import java.util.List;

public interface BoardDAO<T> {
	//등록
	void insert(T t);
	//수정
	void update(T t);
	//삭제
	void delete (int boardNumber);
	//전체조회
	List<T> selectAll();
	//키워드조회
	List<T> selectKeyword(String keyword);
	//게시글번호조회
	T selectOne(int boardNumber);
	
	
}
