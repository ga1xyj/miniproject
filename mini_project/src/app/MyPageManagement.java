package app;

import java.util.List;

import board.Bulletin;
import board.BulletinDAO;
import comment.BulletinComment;
import comment.BulletinCommentDAO;
import common.Management;
import members.Member;
import members.MemberDAO;

public class MyPageManagement extends Management {
	private MemberDAO mDAO = MemberDAO.getInstance();
	private BulletinDAO bDAO = BulletinDAO.getInstance();
	private BulletinCommentDAO bcDAO = BulletinCommentDAO.getInstance();

	public void run() {
		while (true) {
			menuPrint();
			int menuNo = menuSelect();
			if (menuNo == 1) {
				myInfo();
			} else if (menuNo == 2) {
				myBoard();
			} else if (menuNo == 3) {
				myComment();
			} else if (menuNo == 9) {
				return;
			} else {
				showInputError();
			}
		}
	}
	
	@Override
	public void menuPrint() {
		System.out.println("==================================");
		System.out.println("| 1.회원정보 2.게시글 3.댓글 9.뒤로가기 |");
		System.out.println("==================================");
	}
	
	public void myInfo() {
		Member member = mDAO.selectOne(LoginSystem.getLoginInfo());
		System.out.println(member);
	}
	
	public void myBoard() {
		System.out.println("[자유게시판]");
		List<Bulletin> list = bDAO.selectAll(LoginSystem.getLoginInfo().getId());
		for (Bulletin bulletin : list) {
			System.out.println(bulletin);
		}
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	}
	
	public void myComment() {
		System.out.println("[자유게시판 댓글]");
		List<BulletinComment> list = bcDAO.selectAll(LoginSystem.getLoginInfo().getId());
		for (BulletinComment bulletinComment : list) {
			System.out.println(bulletinComment);
		}
		System.out.println("---------------------------------------");
	}
	
}