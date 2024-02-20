package voter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCUtill;

public class VoterDAO {
	Connection conn = null;         //db 연결 및 종료
	PreparedStatement pstmt = null; //sql 처리
	ResultSet rs = null;            //검색한 데이터셋
	
	//좋아요 추가
	public void insertVote(Voter voter) {
		try {
			conn = JDBCUtill.getConnection();
			String sql = "INSERT INTO voter(vno, bno, mid) "
					+ "VALUES(seq_vno.nextval, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, voter.getBno());
			pstmt.setString(2, voter.getMid());
			//sql 실행
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(conn, pstmt);
		}
	}
	
	//해당 게시글의 좋아요 총 개수
	public int voteCount(int bno) {
		try {
			conn = JDBCUtill.getConnection();
			String sql = "SELECT COUNT(*) AS total FROM voter WHERE bno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			// sql 검색
			rs = pstmt.executeQuery();
			if(rs.next()) { //카운트한 값이 있으면 숫자 반환
				return rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(conn, pstmt);
		}
		return 0; //카운트 값이 없으면 0반환
	}
	//좋아요 저장 유무 확인 : 세션 아이디, 글번호가 일치
	public int checkVoter(int bno, String id) {
		try {
			conn = JDBCUtill.getConnection();
			String sql = "SELECT COUNT(*) AS result FROM voter "
					+ "WHERE bno = ? AND mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setString(2, id);
			// sql 검색
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("result"); //일치하면 1이 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(conn, pstmt);
		}
		return 0; //일치하지 않으면 0이 반환
	}
	
	//좋아요 삭제
	public void deleteVote(Voter voter) {
		try {
			conn = JDBCUtill.getConnection();
			String sql = "DELETE FROM voter WHERE bno = ? and mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, voter.getBno());
			pstmt.setString(2, voter.getMid());
			// sql 검색
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(conn, pstmt);
		}
	}
}
