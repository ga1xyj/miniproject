package board;

import java.util.List;

import app.LoginSystem;
import common.Management;
import common.ManagementSystem;

public class NoticeManagement extends Management implements ManagementSystem<Notice> {
	private NoticeDAO nDAO = NoticeDAO.getInstance();

	public void run() {
		boolean role = selectRole();
		while (true) {
			menuPrint(role);
			int menuNo = menuSelect();
			if (menuNo == 1) {
				// 조회
				printAll();
			} else if (menuNo == 2) {
				// 검색
				printKeyword();
			} else if (menuNo == 3) {
				// 등록
				write();
			} else if (menuNo == 9) {
				// 뒤로가기
				back();
				break;
			} else {
				showInputError();
			}
		}
	}
	
	protected boolean selectRole() {
		int memberRole = LoginSystem.getLoginInfo().getRole();
		if(memberRole == 1) {
			return true;
		}else {
			return false;
		}
	}
	
	public void menuPrint(boolean role) {
		if(role) {
			System.out.println("=================================");
			System.out.println("            공지사항            ");
			System.out.println("=================================");
			System.out.println(" 1.조회 2.검색 3.등록 9.뒤로가기 ");
			System.out.println("=================================");
		}else {
			System.out.println("===========================");
			System.out.println("         공지사항         ");
			System.out.println("===========================");
			System.out.println(" 1.조회 2.검색 9.뒤로가기 ");
			System.out.println("===========================");
		}
	}

	// 리스트 출력
	public void printAll() {
		System.out.println("[공지]");
		List<Notice> list = nDAO.selectAll();
		for (Notice notice : list) {
			System.out.println(notice);
		}
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		// 선택한 글만 출력
		choiceClick();
	}

	// 선택한 글만 출력
	public void printOne() {
		// 클릭, 뒤로가기 선택
		int boardNumber = inputNumber();
		Notice notice = nDAO.selectOne(boardNumber);
		System.out.println(notice);
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		// 수정, 삭제 선택
		if(LoginSystem.getLoginInfo().getRole()==1) {
		choiceOption(boardNumber);
		}
	}

	// 클릭, 취소 선택
	public void choiceClick() {
		System.out.println("[1.선택 2.메뉴]");
		int menuNo = menuSelect();
		if (menuNo == 1) {
			printOne();
		} else if (menuNo == 2) {
			return;
		}else {
			showInputError();
		}
	}

	// 수정, 삭제 선택
	public void choiceOption(int no) {
		System.out.println("[1.수정 2.삭제 3.메뉴]");
		int menuNo = menuSelect();
		if (menuNo == 1) {
			// 수정
			updatePost(no);
		} else if (menuNo == 2) {
			// 삭제
			deletePost(no);
		} else if (menuNo == 3) {
			return;
		}else {
			showInputError();
		}
	}

	// 키워드 검색
	public void printKeyword() {
		String keyword = inputKeyword();
		if (keyword == null) {
			System.out.println("검색된 내용이 없습니다.");
			return;
		}
		List<Notice> list = nDAO.selectKeyword(keyword);
		for (Notice notice : list)
			System.out.println(notice);
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		choiceClick();
	}

	// 키워드 입력
	public String inputKeyword() {
		System.out.print("[검색]");
		return sc.nextLine();
	}

	// 게시글 등록
	public void write() {
		Notice notice = inputAll();
		nDAO.insert(notice);
	}

	// 제목, 내용 입력
	public Notice inputAll() {
		Notice notice = new Notice();
		System.out.print("제목|");
		notice.setTitle(sc.nextLine());
		System.out.print("내용|");
		notice.setContent(sc.nextLine());
		return notice;
	}

	// 삭제
	public void deletePost(int boardNumber) {
		Notice notice = nDAO.selectOne(boardNumber);
		nDAO.delete(notice.getBoardNumber());
	}

	// 번호 입력
	public int inputNumber() {
		System.out.print("[번호]");
		return Integer.parseInt(sc.nextLine());
	}

	// 수정
	public void updatePost(int boardNumber) {
		Notice notice = nDAO.selectOne(boardNumber);
		notice = inputUpdate();
		notice.setBoardNumber(boardNumber);
		nDAO.update(notice);
	}

	// 수정 내욕 입력
	public Notice inputUpdate() {
		Notice notice = new Notice();
		System.out.print("제목|");
		notice.setTitle(sc.nextLine());
		System.out.print("내용|");
		notice.setContent(sc.nextLine());
		return notice;
	}

}
