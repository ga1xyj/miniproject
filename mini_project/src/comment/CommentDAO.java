package comment;

import java.util.List;

public interface CommentDAO<T> {
	//등록
		void insert(T t);
		//수정
		void update(T t);
		//삭제
		void delete (T t);
		//전체조회
		List<T> selectAll(int boardNumber);
}
