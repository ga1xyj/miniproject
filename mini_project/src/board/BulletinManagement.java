package board;

import java.util.List;

import app.LoginSystem;
import comment.BulletinComment;
import comment.BulletinCommentDAO;
import common.Management;
import common.ManagementSystem;

public class BulletinManagement extends Management implements ManagementSystem<Bulletin> {
	private BulletinDAO bDAO = BulletinDAO.getInstance();
	private BulletinCommentDAO bcDAO = BulletinCommentDAO.getInstance();

	public void run() {
		while (true) {
			menuPrint();
			int menuNo = menuSelect();
			if (menuNo == 1) {
				printAll();
			} else if (menuNo == 2) {
				printKeyword();
			} else if (menuNo == 3) {
				write();
			} else if (menuNo == 9) {
				back();
				break;
			} else {
				showInputError();
			}
		}
	}

	// 게시글 출력
	@Override
	public void printAll() {
		List<Bulletin> list = bDAO.selectAll();
		for (Bulletin bulletin : list) {
			System.out.println(bulletin);
		}
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		choiceClick();
	}

	// 게시글 선택 메뉴
	@Override
	public void choiceClick() {
		System.out.println("[1.선택 2.메뉴]");
		int menuNo = menuSelect();
		if (menuNo == 1) {
			printOne();
		} else if (menuNo == 2) {
			return;
		} else {
			showInputError();
		}
	}

	// 게시글 선택 출력
	@Override
	public void printOne() {
		int boardNumber = inputNumber();
		Bulletin bulletin = bDAO.selectOne(boardNumber);
		System.out.println(bulletin);
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");

		// 같은 작성자일시 게시글 수정,삭제 메뉴
		if (LoginSystem.getLoginInfo().getId().equals(bulletin.getId())) {
			choiceOption(boardNumber);
		}
		// 댓글 전체 출력
		
		printComment(boardNumber);
		commentMenu(boardNumber);
	}

	// 댓글 입력
	public BulletinComment inputComment(int no) {
		BulletinComment bulletinComment = new BulletinComment();
		System.out.print("댓글|");
		bulletinComment.setContent(sc.nextLine());
		bulletinComment.setBoardNumber(no);
		return bulletinComment;
	}

	// 댓글 출력
	public void printComment(int no) {
		List<BulletinComment> list = bcDAO.selectAll(no);
		for (BulletinComment bulletinComment : list) {
			System.out.println(bulletinComment);
			if(LoginSystem.getLoginInfo().getId().equals(bcDAO.selectOne(no).getId())) {
			choiceCommentOption(no);
			}
		} 
		System.out.println("---------------------------------------");
	}

	// 댓글 작성
	public void writeComment(int no) {
		BulletinComment bulletinComment = inputComment(no);
		bcDAO.insert(bulletinComment);
	}

	// 댓글 메뉴
	public void commentMenu(int no) {
		String menu = "[1.댓글 2.메뉴]";
		System.out.println(menu);
		int menuNo = menuSelect();
		if (menuNo == 1) {
			// 댓글달기
			writeComment(no);
		} else if (menuNo == 2) {
			return;
		} else {
			showInputError();
		}
	}

	// 댓글 수정
	public void updateComment(int no) {
		BulletinComment bulletinComment = bcDAO.selectOne(no);
		System.out.print("댓글|");
		bulletinComment.setContent(sc.nextLine());
		bcDAO.update(bulletinComment);
		}

	// 댓글 삭제
	public void deleteComment(int no) {
		BulletinComment bulletinComment = bcDAO.selectOne(no);
		bcDAO.delete(bulletinComment);
	}

	// 게시글 수정 삭제 옵션
	@Override
	public void choiceOption(int no) {
		System.out.println("[1.수정 2.삭제 3.취소]");
		int menuNo = menuSelect();
		if (menuNo == 1) {
			// 수정
			updatePost(no);
		} else if (menuNo == 2) {
			// 삭제
			deletePost(no);
		} else if (menuNo == 3) {
			return;
		} else {
			showInputError();
		}
	}
	
	//댓글 수정 삭제 옵션
	public void choiceCommentOption(int no) {
		System.out.println("[1.수정 2.삭제 3.취소]");
		int menuNo = menuSelect();
		if (menuNo == 1) {
			// 수정
			updateComment(no);
		} else if (menuNo == 2) {
			// 삭제
			deleteComment(no);
		} else if (menuNo == 3) {
			return;
		} else {
			showInputError();
		}
	}
	

	// 번호 입력
	@Override
	public int inputNumber() {
		System.out.print("[번호]");
		return Integer.parseInt(sc.nextLine());
	}

	// 키워드 검색
	@Override
	public void printKeyword() {
		String keyword = inputKeyword();
		if (keyword == null) {
			System.out.println("검색된 내용이 없습니다.");
			return;
		}
		List<Bulletin> list = bDAO.selectKeyword(keyword);
		for (Bulletin bulletin : list)
			System.out.println(bulletin);
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		choiceClick();
	}

	// 키워드 입력
	@Override
	public String inputKeyword() {
		System.out.print("[검색]");
		return sc.nextLine();
	}

	// 게시글 작성
	@Override
	public void write() {
		Bulletin bulletin = inputAll();
		bDAO.insert(bulletin);
	}

	// 게시글 입력
	@Override
	public Bulletin inputAll() {
		Bulletin bulletin = new Bulletin();
		System.out.print("제목|");
		bulletin.setTitle(sc.nextLine());
		System.out.print("내용|");
		bulletin.setContent(sc.nextLine());
		return bulletin;
	}

	// 게시글 수정
	@Override
	public void updatePost(int boardNumber) {
		Bulletin bulletin = bDAO.selectOne(boardNumber);
		bulletin = inputAll();
		bulletin.setBoardNumber(boardNumber);
		bDAO.update(bulletin);
	}

	// 게시글 삭제
	@Override
	public void deletePost(int boardNumber) {
		Bulletin bulletin = bDAO.selectOne(boardNumber);
		bDAO.delete(bulletin.getBoardNumber());
	}
}
