package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import oracle.jdbc.OracleDriver;

public class DemoC {
	
	public void test() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("조회할 이름의 첫 문자(영문자) : ");
		String name = scanner.nextLine();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//1. JDBC 드라이버 준비
			Class.forName("oracle.jdbc.OracleDriver");
			//DriverManager.registerDriver(new OracleDriver());
			
			//2. 연결 객체 만들기 (연결)
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@172.16.6.31:1521:xe",
					"hr", "9922");
			
			//3. SQL 작성 + 명령 객체 만들기
			String sql = 
				"SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, DEPARTMENT_ID " +
				"FROM EMPLOYEES " + 
				"WHERE FIRST_NAME LIKE ? OR LAST_NAME LIKE ?" ; //?: 데이터가 채우질 곳
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name + "%"); // ?를 채울 데이터
			pstmt.setString(2, name + "%"); // ?를 채울 데이터
			
			//4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : SELECT를 위한 명령
			
			//5. 명령 실행 결과가 있으면 처리 (SELECT 인 경우)
			while (rs.next()) { // 결과 집합의 다음 행으로 이동 (없으면 false) 
				System.out.printf("[%d][%s][%s][%d]\n", 
					rs.getInt(1),		//현재 행의 첫 번째 컬럼의 정수 읽기 
					rs.getString(2), 	//현재 행의 두 번째 컬럼의 문자열 읽기
					rs.getString("LAST_NAME"), 
					rs.getInt(4));
			}			
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 오류 메시지 출력
		} finally {
			//6. 연결 종료			
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}
	

	public static void main(String[] args) {

		DemoC demoA = new DemoC();
		demoA.test();

	}

}