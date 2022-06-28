package comment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.LoginSystem;
import board.Bulletin;
import common.DAO;

public class BulletinCommentDAO extends DAO implements CommentDAO<BulletinComment>{
	private static BulletinCommentDAO bulletinCDAO = null;
	public static BulletinCommentDAO getInstance() {
		if(bulletinCDAO == null) {
			bulletinCDAO = new BulletinCommentDAO();
		}
		return bulletinCDAO;
	}

	@Override
	public void insert(BulletinComment bulletinComment) {
		try {
			connect();
			String sql = "INSERT INTO bulletin_comment (bc_number, board_number, id, content) VALUES (bc_seq.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bulletinComment.getBoardNumber());
			pstmt.setString(2, LoginSystem.getLoginInfo().getId());
			pstmt.setString(3, bulletinComment.getContent());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("등록되었습니다.");
			} else {
				System.out.println("등록되지 않았습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	@Override
	public void update(BulletinComment bulletinComment) {
		try {
			connect();
			String sql = "UPDATE bulletin_comment SET content = ? WHERE board_number = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bulletinComment.getContent());
			pstmt.setInt(2, bulletinComment.getBoardNumber());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("수정되었습니다.");
			} else {
				System.out.println("수정되지 않았습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	@Override
	public void delete(BulletinComment bulletinComment) {
		try {
			connect();
			String sql = "DELETE FROM bulletin_comment WHERE board_number = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bulletinComment.getBoardNumber());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("삭제되었습니다.");
			} else {
				System.out.println("삭제되지 않았습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	//댓글 전체 출력
	@Override
	public List<BulletinComment> selectAll(int boardNumber) {
		List<BulletinComment> list = new ArrayList<BulletinComment>();
		try {
			connect();
			String sql = "SELECT ROWNUM, bulletin_comment.* FROM bulletin_comment WHERE board_number = ? ORDER BY ROWNUM DESC";
			//String sql = "SELECT * FROM bulletin_comment WHERE board_number = ? ORDER BY comment_number DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNumber);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BulletinComment bulletinComment = new BulletinComment();
				bulletinComment.setCommentNumber(rs.getInt("ROWNUM"));
				//bulletinComment.setBoardNumber(rs.getInt("board_mumber"));
				//bulletinComment.setId(rs.getString("id"));
				bulletinComment.setContent(rs.getString("content"));
				bulletinComment.setBoardDate(rs.getDate("board_date"));
				list.add(bulletinComment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	//내 아이디로 쓴 댓글 출력
	public List<BulletinComment> selectAll(String id) {
		List<BulletinComment> list = new ArrayList<BulletinComment>();
		try {
			connect();
			String sql = "SELECT * FROM bulletin_comment WHERE id = ? ORDER BY comment_number DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BulletinComment bulletinComment = new BulletinComment();
				bulletinComment.setCommentNumber(rs.getInt("comment_number"));
				//bulletinComment.setBoardNumber(rs.getInt("board_mumber"));
				bulletinComment.setId(rs.getString("id"));
				bulletinComment.setContent(rs.getString("content"));
				bulletinComment.setBoardDate(rs.getDate("board_date"));
				list.add(bulletinComment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	
	//선택용
	public BulletinComment selectOne(int boardNumber) {
		BulletinComment bulletinComment = null;
		try {
			connect();
			String sql = "SELECT * FROM bulletin_comment WHERE board_number = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNumber);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bulletinComment =  new BulletinComment();
				bulletinComment.setCommentNumber(rs.getInt("comment_number"));
				bulletinComment.setBoardNumber(rs.getInt("board_number"));
				bulletinComment.setId(rs.getString("id"));
				bulletinComment.setContent(rs.getString("content"));
				bulletinComment.setBoardDate(rs.getDate("board_date"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return bulletinComment;
	}
	
	/*
	public BulletinComment selectOne(int boardNumber, int commentNumber) {
		BulletinComment bulletinComment = null;
		try {
			connect();
			String sql = "SELECT * FROM bulletin_comment WHERE comment_number = ? AND board_number = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentNumber);
			pstmt.setInt(2, boardNumber);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bulletinComment =  new BulletinComment();
				bulletinComment.setCommentNumber(rs.getInt("comment_number"));
				bulletinComment.setBoardNumber(rs.getInt("board_number"));
				bulletinComment.setId(rs.getString("id"));
				bulletinComment.setContent(rs.getString("content"));
				bulletinComment.setBoardDate(rs.getDate("board_date"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return bulletinComment;
	}
	*/
	

}
