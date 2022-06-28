package common;

import java.util.Scanner;

import app.MyPageManagement;
import board.BulletinManagement;
import board.NoticeManagement;

public class Management {
	protected Scanner sc = new Scanner(System.in);
	
	public void run() {
		while (true) {
			menuMainPrint();
			int menuNo = menuSelect();
			if (menuNo == 1) {
				new NoticeManagement().run();
			} else if (menuNo == 2) {
				new BulletinManagement().run();
			} //else if (menuNo == 3) {

			//} 
				else if (menuNo == 3) {
				new MyPageManagement().run();
			} else if (menuNo == 9) {
				backHome();
				break;
			} else {
				showInputError();
			}
		}
	}

	protected void menuMainPrint() {
		System.out.println("==========================================");
		System.out.println("| 1.공지사항 2.자유게시판 3.마이페이지 9.뒤로가기 |");
		System.out.println("==========================================");
	}

	protected int menuSelect() {
		int menuNo = 0;
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력해주시기 바랍니다.");
		}
		return menuNo;
	}

	protected void backHome() {
		System.out.println("홈으로 돌아갑니다.");
	}

	protected void showInputError() {
		System.out.println("메뉴에서 입력해주시기 바랍니다.");
	}
	
	public void menuPrint() {
		System.out.println("==============================");
		System.out.println("| 1.조회 2.검색 3.등록 9.뒤로가기 |");
		System.out.println("==============================");
	}
	
	public void back() {
		System.out.println("메인으로 돌아갑니다.");
	}
	
	

}
