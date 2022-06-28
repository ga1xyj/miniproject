package board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.LoginSystem;
import common.BoardDAO;
import common.DAO;
import oracle.net.aso.e;

public class BulletinDAO extends DAO implements BoardDAO<Bulletin> {
	private static BulletinDAO bulletinDAO = null;
	
	private BulletinDAO() {}
	
	public static BulletinDAO getInstance() {
		if(bulletinDAO == null) {
			bulletinDAO = new BulletinDAO();
		}
		return bulletinDAO;
	}

	@Override
	public void insert(Bulletin bulletin) {
		try {
			connect();
			String sql = "INSERT INTO bulletin (b_number, board_number, id, title, content) VALUES (bb_seq.nextval, (SELECT NVL(MAX(board_number)+1,1) FROM bulletin), ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, LoginSystem.getLoginInfo().getId());
			pstmt.setString(2, bulletin.getTitle());
			pstmt.setString(3, bulletin.getContent());
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
	public void update(Bulletin bulletin) {
		try {
			connect();
			String sql = "UPDATE bulletin SET title = ?, content = ? WHERE board_number = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bulletin.getTitle());
			pstmt.setString(2, bulletin.getContent());
			pstmt.setInt(3, bulletin.getBoardNumber());
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
	public void delete(int boardNumber) {
		try {
			connect();
			String sql = "DELETE FROM bulletin WHERE board_number =" + boardNumber;
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

	//전체조회
	@Override
	public List<Bulletin> selectAll() {
		List<Bulletin> list = new ArrayList<Bulletin>();
		try {
			connect();
			String sql = "SELECT * FROM bulletin ORDER BY board_number DESC";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Bulletin bulletin = new Bulletin();
				bulletin.setBoardNumber(rs.getInt("board_number"));
				bulletin.setId(rs.getString("id"));
				bulletin.setTitle(rs.getString("title"));
				bulletin.setContent(rs.getString("content"));
				bulletin.setBoardDate(rs.getDate("board_date"));
				list.add(bulletin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	public List<Bulletin> selectAll(String id) {
		List<Bulletin> list = new ArrayList<Bulletin>();
		try {
			connect();
			String sql = "SELECT * FROM bulletin WHERE id = ? ORDER BY board_number DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bulletin bulletin = new Bulletin();
				bulletin.setBoardNumber(rs.getInt("board_number"));
				bulletin.setId(rs.getString("id"));
				bulletin.setTitle(rs.getString("title"));
				bulletin.setContent(rs.getString("content"));
				bulletin.setBoardDate(rs.getDate("board_date"));
				list.add(bulletin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	//키워드 조회
	@Override
	public List<Bulletin> selectKeyword(String keyword) {
		List<Bulletin> list = new ArrayList<Bulletin>();
		try {
			connect();
			String sql = "SELECT * FROM bulletin WHERE title LIKE '%" + keyword + "%' OR content LIKE '%" + keyword + "%' ORDER BY board_number DESC";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Bulletin bulletin = new Bulletin();
				bulletin.setBoardNumber(rs.getInt("board_number"));
				bulletin.setId(rs.getString("id"));
				bulletin.setTitle(rs.getString("title"));
				bulletin.setContent(rs.getString("content"));
				bulletin.setBoardDate(rs.getDate("board_date"));
				list.add(bulletin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	//내용 조회(글 선택)
	@Override
	public Bulletin selectOne(int boardNumber) {
		Bulletin bulletin = null;
		try {
			connect();
			String sql = "SELECT * FROM bulletin WHERE board_number = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNumber);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bulletin =  new Bulletin();
				bulletin.setBoardNumber(rs.getInt("board_number"));
				bulletin.setId(rs.getString("id"));
				bulletin.setTitle(rs.getString("title"));
				bulletin.setContent(rs.getString("content"));
				bulletin.setBoardDate(rs.getDate("board_date"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return bulletin;
	}

}
