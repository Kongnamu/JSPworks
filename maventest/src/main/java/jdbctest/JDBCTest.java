package jdbctest; //Sql과 연동하기

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest {

	public static void main(String[] args) {
		Connection conn = null;
		
		String driverClass = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/jwebdb?serverTime=Asia/Seoul"; //오라클 jweb 속성들어가면 확인 가능
		String user = "jweb"; //아이디
		String password = "pwjweb"; //비번
		
		try {
			Class.forName(driverClass); //오라클sql 연결 드라이버
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("db 연결 성공: " + conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
