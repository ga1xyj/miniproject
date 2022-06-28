package members;

import java.sql.SQLException;
import common.DAO;

public class MemberDAO extends DAO {
	private static MemberDAO mdao = null;
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		if (mdao == null) {
			mdao = new MemberDAO();
		}
		return mdao;
	}
	
	//로그인
	public Member selectOne(Member member) {
		Member loginInfo = null;
		try {
			connect();
			String sql = "SELECT * FROM members WHERE id = '" + member.getId() + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				if(rs.getString("pwd").equals(member.getPwd())) {
				member.setRole(rs.getInt("role"));
				loginInfo = new Member();
				loginInfo.setName(rs.getString("name"));
				loginInfo.setDepartmentName(rs.getString("department_name"));
				loginInfo.setStudentNumber(rs.getInt("student_number"));
				loginInfo.setId(rs.getString("id"));
				loginInfo.setPwd(rs.getString("pwd"));
				loginInfo.setRole(rs.getInt("role"));
				}else {
					System.out.println("비밀번호가 일치하지 않습니다.");
				}
			}else {
				System.out.println("아이디가 존재하지 않습니다.");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return loginInfo;
	}
	
	//회원가입
	public void Insert(Member member) {
		try {
			connect();
			String sql = "INSERT INTO members (name, department_name, student_number, id, pwd) VALUES (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getDepartmentName());
			pstmt.setInt(3, member.getStudentNumber());
			pstmt.setString(4, member.getId());
			pstmt.setString(5, member.getPwd());
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("회원가입하였습니다.");
			}else {
				System.out.println("회원가입에 실패하였습니다.");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	
}
