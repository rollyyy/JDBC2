package com.greedy.section01.insert;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.getConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Application2 {

	public static void main(String[] args) {
		
		Connection con = getConnection();
		
		PreparedStatement pstmt = null;
		/* SELECT 수행 시 결과 값 ResultSet 객체
		 * INSERT/UPDATE/DELTE 수행 시 결과 값 삽입/수정/삭제 된 행의 개수 (int) 
		 * */
		int result = 0;
		
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-query.xml"));
			String query = prop.getProperty("insertMenu");
			System.out.println(query);
			
			Scanner sc = new Scanner(System.in);
			System.out.print("메뉴의 이름을 입력하세요 : ");
			String menuName = sc.nextLine();
			System.out.print("메뉴의 가격을 입력하세요 : ");
			int menuPrice = sc.nextInt();
			System.out.print("카테고리 코드를 입력하세요 : ");
			int categoryCode = sc.nextInt();
			System.out.print("판매 여부를 결정해주세요(Y/N) : ");
			sc.nextLine();
			String orderableStatus = sc.nextLine().toUpperCase();
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, menuName);
			pstmt.setInt(2, menuPrice);
			pstmt.setInt(3, categoryCode);
			pstmt.setString(4, orderableStatus);
			
			/* select시 executeQuery()
			 * insert/update/delete시 executeUpdate()
			 * */
			result = pstmt.executeUpdate();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		
		System.out.println("result : " + result);
	
	
		
		
	}

}
