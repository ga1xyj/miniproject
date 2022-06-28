package app;

import java.util.Scanner;

import common.Management;
import members.Member;
import members.MemberDAO;

public class LoginSystem {
	private Scanner sc = new Scanner(System.in);
	private static Member loginInfo = null;

	public static Member getLoginInfo() {
		return loginInfo;
	}

	private MemberDAO mDAO = MemberDAO.getInstance();

	// 생성자
	public LoginSystem() {
		while (true) {
			menuPrint();
			int menuNo = menuSelect();
			if (menuNo == 1) {
				// 로그인
				login();
			} else if (menuNo == 2) {
				// 회원가입
				signUp();
			} else if (menuNo == 3) {
				exit();
				break;
			} else {
				showInputError();
			}
		}
	}

	private void menuPrint() {
		System.out.println("=========================");
		System.out.println("| 1.로그인 2.회원가입 3.종료 |");
		System.out.println("=========================");
	}

	private int menuSelect() {
		int menuNo = 0;
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("숫자 형식으로 입력해주세요.");
		}
		return menuNo;
	}

	private void exit() {
		System.out.println("프로그램을 종료합니다.");
	}

	private void showInputError() {
		System.out.println("메뉴를 확인해주시기 바랍니다.");
	}

	private void login() {
		while (true) {
			Member inputInfo = inputMember();
			loginInfo = MemberDAO.getInstance().selectOne(inputInfo);
			if (loginInfo != null)
				new Management().run();
			break;
		}
	}

	private Member inputMember() {
		Member loginInfo = new Member();
		System.out.print("아이디>");
		loginInfo.setId(sc.nextLine());
		System.out.print("비밀번호>");
		loginInfo.setPwd(sc.nextLine());
		return loginInfo;
	}

	private void signUp() {
		Member member = inputAll();
		mDAO.Insert(member);
	}

	private Member inputAll() {
		Member member = new Member();
		System.out.print("이름>");
		member.setName(sc.nextLine());
		System.out.print("학과>");
		member.setDepartmentName(sc.nextLine());
		System.out.print("학번>");
		member.setStudentNumber(Integer.parseInt(sc.nextLine()));
		System.out.print("ID>");
		member.setId(sc.nextLine());
		System.out.print("PASSWORD>");
		member.setPwd(sc.nextLine());
		return member;
	}

}
