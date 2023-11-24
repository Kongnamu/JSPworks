package adressbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtill;

//AddrBook 자료형을 이동하여 주소를 생성, 검색, 수정, 삭제를 담당하는 클래스
public class AddrBookDAO {
	//Jdbc 관련 변수
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; // DB에 있는 자료 꺼내올떄 (검색할때만)
	
	//주소 추가
	public void addAddrBook(AddrBook addrBook) {
		//db 연결
		conn = JDBCUtill.getConnection();
		try {
		//sql 처리
		String sql = "INSERT INTO addrbook(bnum, username, tel, email, gender) "
				+ "VALUES (seq_bnum.NEXTVAL, ?, ?, ?, ?)";
		
				pstmt = conn.prepareStatement(sql);
				//폼에 입력된 자료를 가져와서 DB에 저장
				pstmt.setString(1, addrBook.getUsername());
				pstmt.setString(2, addrBook.getTel());
				pstmt.setString(3, addrBook.getEmail());
				pstmt.setString(4, addrBook.getGender());
				
				//sql 실행
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtill.close(conn, pstmt, rs);
			}
			
	}
	
	//주소 전체 목록
	public List<AddrBook> getListAll(){
		//DB 연결
		conn = JDBCUtill.getConnection();
		List<AddrBook> addrList = new ArrayList<>(); 
		
		try {
			//SQL 처리
		String sql = "SELECT * FROM addrbook ORDER BY bnum"; //오름차순 정렬
			pstmt = conn.prepareStatement(sql);
			//db의 주소록을 꺼내옴
			rs = pstmt.executeQuery();
			//반복문을 써서 다시 화면에 뿌려줌
			while(rs.next()) { // 다음 자료가 있으면
				AddrBook addrBook = new AddrBook(); // 주소 넣기위해 빈객체 생성
				addrBook.setBnum(rs.getInt("bnum"));// DB에 있는 주소를 가져와서 주소 객체에 저장(화면 목록)
				addrBook.setUsername(rs.getString("username"));
				addrBook.setTel(rs.getString("tel"));
				addrBook.setEmail(rs.getString("email"));
				addrBook.setGender(rs.getString("gender"));
				addrBook.setRegDate(rs.getTimestamp("regdate")); //setRegDate : 자바에선 앞에 대문자 써야함
				
				//리스트에 1개 객체를 순서에따라 저장 (인덱싱)
				addrList.add(addrBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { //db종료
			JDBCUtill.close(conn, pstmt, rs);
		}
		return addrList;
	}
	// 주소 정보 1건 보기
	 public AddrBook getAddrBook(int bnum) {
		//DB 연결
			conn = JDBCUtill.getConnection();
			AddrBook addrBook = new AddrBook(); // 주소 넣기위해 빈객체 생성
			try {
		//SQL 처리
			String sql = "SELECT * FROM addrbook WHERE bnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bnum);
			//bnum이 일치하는 1개의 주소를 가져옴
			rs = pstmt.executeQuery();
			if(rs.next()) {
				addrBook.setBnum(rs.getInt("bnum"));// DB에 있는 주소를 가져와서 주소 객체에 저장(화면 목록)
				addrBook.setUsername(rs.getString("username"));
				addrBook.setTel(rs.getString("tel"));
				addrBook.setEmail(rs.getString("email"));
				addrBook.setGender(rs.getString("gender"));
				addrBook.setRegDate(rs.getTimestamp("regdate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(conn, pstmt, rs);
		}
		return addrBook;
	 }
	 
	 //로그인 체크
	 public boolean checkLogin(String email) {
		 conn = JDBCUtill.getConnection();
		  
		 try {
			 String sql = "SELECT email FROM addrbook "
				 		+ "WHERE email = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) { //검색한 이메일이 있으면
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(conn, pstmt, rs);
		}
		 
		 return false;
	 }
	 //주소 삭제
	 public void deleteAddrBook(int bnum) {
		 //db연결
		 conn = JDBCUtill.getConnection();
		 
		 try {
			 //sql처리
			 String sql = "DELETE FROM addrbook WHERE bnum = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bnum); //외부에서받은 번호를 입력함
			//sql실행
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { //db 종료
			JDBCUtill.close(conn, pstmt);
		}
	 }
	 // 주소 수정
	 public void updateAddrBook(AddrBook addrBook) {
		//db연결
		 conn = JDBCUtill.getConnection();
		 
		 try {
			//sql처리
			 String sql = "UPDATE addrbook "
			 		+ "SET username = ?, tel = ?, email = ?, gender = ?"
			 		+ "WHERE bnum = ?";
			 pstmt = conn.prepareStatement(sql); // sql로 처리하겠다
			 //폼에 입력된 데이터를 가져와서 db에 저장
			 pstmt.setString(1, addrBook.getUsername());
			 pstmt.setString(2, addrBook.getTel());
			 pstmt.setString(3, addrBook.getEmail());
			 pstmt.setString(4, addrBook.getGender());
			 pstmt.setInt(5, addrBook.getBnum());
			//sql실행
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { //db 종료
			JDBCUtill.close(conn, pstmt);
		}
	 }
}
