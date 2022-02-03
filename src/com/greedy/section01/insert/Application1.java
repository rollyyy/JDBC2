package com.greedy.section01.insert;

import static com.greedy.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Application1 {

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
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "봉골레청국장");
			pstmt.setInt(2, 50000);
			pstmt.setInt(3, 4);
			pstmt.setString(4, "Y");
			
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
