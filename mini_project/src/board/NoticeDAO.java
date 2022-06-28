package board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.BoardDAO;
import common.DAO;

public class NoticeDAO extends DAO implements BoardDAO<Notice>{
	// 싱글톤
	private static NoticeDAO noticeDAO = null;

	private NoticeDAO() {}

	public static NoticeDAO getInstance() {
		if (noticeDAO == null) {
			noticeDAO = new NoticeDAO();
		}
		return noticeDAO;
	}

	// 등록
	public void insert(Notice notice) {
		try {
			connect();
			String sql = "INSERT INTO notice (board_number, title, content) VALUES (notice_seq.nextval, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
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

	// 수정
	public void update(Notice notice) {
		try {
			connect();
			String sql = "UPDATE notice SET title = ?, content = ? WHERE board_number = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
			pstmt.setInt(3, notice.getBoardNumber());
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

	// 삭제
	public void delete(int boardNumber) {
		try {
			connect();
			String sql = "DELETE FROM notice WHERE board_number =" + boardNumber;
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
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

	//전체조회-제목만 출력
	public List<Notice> selectAll() {
		List<Notice> list = new ArrayList<Notice>();
		try {
			connect();
			String sql = "SELECT board_number, board_date, title FROM notice ORDER BY board_number DESC";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Notice notice = new Notice();
				notice.setBoardNumber(rs.getInt("board_number"));
				notice.setTitle(rs.getString("title"));
				//notice.setContent(rs.getString("content"));
				notice.setBoardDate(rs.getDate("board_date"));
				list.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	//키워드조회-제목만 출력
	public List<Notice> selectKeyword(String keyword) {
		List<Notice> list = new ArrayList<Notice>();
		try {
			connect();
			String sql = "SELECT board_number, board_date, title FROM notice WHERE title LIKE '%" + keyword + "%' OR content LIKE '%" + keyword + "%' ORDER BY board_number DESC";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Notice notice = new Notice();
				notice.setBoardNumber(rs.getInt("board_number"));
				notice.setTitle(rs.getString("title"));
				//notice.setContent(rs.getString("content"));
				notice.setBoardDate(rs.getDate("board_date"));
				list.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	//내용 조회(글 선택)
	public Notice selectOne(int boardNumber) {
		Notice notice = null;
		try {
			connect();
			String sql = "SELECT * FROM notice WHERE board_number = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNumber);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				notice = new Notice();
				notice.setBoardNumber(rs.getInt("board_number"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setBoardDate(rs.getDate("board_date"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return notice;
	}
	
}
