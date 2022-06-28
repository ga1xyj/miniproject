package common;

public interface ManagementSystem<T> {
	void run();
	void printAll();
	//선택 출력
	void printOne();
	//클릭, 취소 선택
	void choiceClick();
	//수정, 삭제 옵션
	void choiceOption(int no);
	//수정
	void updatePost(int boardNumber);
	//삭제
	void deletePost(int boardNumber);
	//번호 입력
	int inputNumber();
	//검색 출력
	void printKeyword();
	//키워드 입력
	String inputKeyword();
	//게시글 등록
	void write();
	//제목내용 입력
	T inputAll();
}
