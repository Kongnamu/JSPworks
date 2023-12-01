package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtill;

public class BoardDAO {
	
	Connection conn = null;         //db 연결 및 종료
	PreparedStatement pstmt = null; //sql 처리
	ResultSet rs = null;            //검색한 데이터셋

	//게시글 목록
	public List<Board> gerBoardList(){
		List<Board> boardList = new ArrayList<>();
		
		try {
			//db 연결
			conn = JDBCUtill.getConnection();
			//sql 처리
			String sql = "SELECT * FROM board ORDER BY createdate DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board b = new Board();
				b.setBno(rs.getInt("bno"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setCreateDate(rs.getTimestamp("createdate"));
				b.setModifyDate(rs.getTimestamp("modifydate"));
				b.setHit(rs.getInt("hit"));
				b.setFilename(rs.getString("filename"));
				b.setId(rs.getString("id"));
				
				boardList.add(b); //list에 b 객체 저장함
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(conn, pstmt, rs);
		}
		return boardList;
	}
	
	//글쓰기 처리
	public void write(Board b) {
		try {
			//db 연결
			conn = JDBCUtill.getConnection();
			//sql 처리
			String sql = "INSERT INTO board (bno, title, content, id) "
					+ "VALUES (seq_bno.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setString(3, b.getId());
			//sql 실행
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(conn, pstmt);
		}
	}
	
	//게시글 상세보기
		public Board getBoard(int bno) {
			Board b = new Board();
			try {
				conn = JDBCUtill.getConnection();
				String sql = "SELECT * FROM board WHERE bno = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bno);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					b.setBno(rs.getInt("bno"));
					b.setTitle(rs.getString("title"));
					b.setContent(rs.getString("content"));
					b.setCreateDate(rs.getTimestamp("createdate"));
					b.setModifyDate(rs.getTimestamp("modifydate"));
					b.setHit(rs.getInt("hit"));
					b.setFilename(rs.getString("filename"));
					b.setId(rs.getString("id"));
					
					//조회수 1증가
					sql = "UPDATE BOARD SET hit = hit + 1 "
							+ "WHERE bno = ?";
					pstmt = conn.prepareStatement(sql);
					/* pstmt.setInt(1, rs.getInt("hit") + 1); */
					pstmt.setInt(1, bno);
					pstmt.executeUpdate(); //실행
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtill.close(conn, pstmt, rs);
			}
			return b;
		}
		
	//게시글 삭제
		public void deleteboard(int bno) {
			try {
				//db 연결
				conn = JDBCUtill.getConnection();
				//sql 처리
				String sql = "DELETE FROM board WHERE bno = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bno);
				//sql 실행
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtill.close(conn, pstmt);
			}
		}
	// 게시글 수정 : 가입과 비슷하나 수정해서 가입시킨다고 이해하면 좋음
		public void updateboard(Board b) {
			//현재 날짜와 시간 객체 생성
			Timestamp now = new Timestamp(System.currentTimeMillis());
			try {
				//db 연결
				conn = JDBCUtill.getConnection();
				//sql 처리 : 수정일 처리는 현재 날짜와 시간을 입력
				String sql = "UPDATE board SET title = ?, content = ?, modifydate = ? "
						+ "WHERE bno = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, b.getTitle());
				pstmt.setString(2, b.getContent());
				pstmt.setTimestamp(3, now); //수정일
				pstmt.setInt(4, b.getBno());
				//sql 실행
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtill.close(conn, pstmt);
			}
		}
}//dao클래스 닫기
