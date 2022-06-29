package app;

import java.util.List;

import board.Bulletin;
import board.BulletinDAO;
import board.BulletinManagement;
import comment.BulletinComment;
import comment.BulletinCommentDAO;
import common.Management;
import members.Member;
import members.MemberDAO;

public class MyPageManagement extends BulletinManagement {
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
		System.out.println("==============================================");
		System.out.println("                    마이페이지                ");
		System.out.println("==============================================");
		System.out.println(" 1.회원정보 2.내가 쓴 글 3.댓글단 글 9.뒤로가기 ");
		System.out.println("==============================================");
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
		choiceClick();
	}
	
	/*
	public void myComment() {
		System.out.println("[자유게시판 댓글]");
		List<BulletinComment> list = bcDAO.selectAll(LoginSystem.getLoginInfo().getId());
		for (BulletinComment bulletinComment : list) {
			System.out.println("---------------------------------------");
			System.out.println(bulletinComment.getContent());
			System.out.println(bulletinComment.getBoardDate());
		}
		System.out.println("---------------------------------------");
	}*/
	
	public void myComment() {
		System.out.println("[자유게시판]");
		List<Bulletin> list = bDAO.selectAllComment(LoginSystem.getLoginInfo().getId());
		for (Bulletin bulletin : list) {
			System.out.println(bulletin);
		}
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		choiceClick();
	}
	
	
}