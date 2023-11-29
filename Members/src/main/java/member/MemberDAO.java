package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtill;

public class MemberDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//회원 목록
	public List<Member> getMemberList(){
		List<Member> memberList = new ArrayList<>();
		try {
		//db연결
		conn = JDBCUtill.getConnection();
			
		//sql처리
		String sql = "SELECT * FROM member ORDER BY mno";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			//빈 객체 생성, db에서 정보를 가져와 세팅
			Member m = new Member();
			m.setMno(rs.getInt("mno"));
			m.setId(rs.getString("id"));
			m.setPasswd(rs.getString("passwd"));
			m.setName(rs.getString("name"));
			m.setEmail(rs.getString("email"));
			m.setGender(rs.getString("gender"));
			m.setJoinDate(rs.getTimestamp("joindate"));
			//리스트에 회원 추가
			memberList.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { //db 종료
			JDBCUtill.close(conn, pstmt, rs);
		}
		
		return memberList;
	}
	
	//회원 가입
	public void insertMember(Member m) {
		
		try {
			conn = JDBCUtill.getConnection();
			
			String sql = "INSERT INTO member (mno, id, passwd, name, email, gender) "
					+ "VALUES (seq_mno.NEXTVAL, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			//폼에 입력된 데이터를 가져와서 db에 저장함
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPasswd());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getGender());
			//sql 실행
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(conn, pstmt);
		}
	}
}
