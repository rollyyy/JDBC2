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

import com.greedy.model.dto.MenuDTO;

public class Application3 {

	public static void main(String[] args) {
		
		/* 다른 쪽 클래스에서 작성한다고 가정함 */
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
		
		MenuDTO newMenu = new MenuDTO();
		newMenu.setName(menuName);
		newMenu.setPrice(menuPrice);
		newMenu.setCategoryCode(categoryCode);
		newMenu.setOrderableStatus(orderableStatus);
		
		/* ------------------------------------------- */
		/* 값을 뭉쳐서 보내기 위해 DTO에 담고 전송 */
		/* ------------------------------------------- */
		
		Connection con = getConnection();
		
		PreparedStatement pstmt = null;
		/* SELECT 수행 시 결과 값 ResultSet 객체
		 * INSERT/UPDATE/DELETE 수행 시 결과 값 삽입/수정/삭제 된 행의 개수 (int) 
		 * */
		int result = 0;
		
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-query.xml"));
			String query = prop.getProperty("insertMenu");
			System.out.println(query);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, newMenu.getName());
			pstmt.setInt(2, newMenu.getPrice());
			pstmt.setInt(3, newMenu.getCategoryCode());
			pstmt.setString(4, newMenu.getOrderableStatus());
			
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
		
		if(result > 0) {
			System.out.println("메뉴 등록에 성공하였습니다.");
		} else {
			System.out.println("메뉴 등록에 실패하였습니다.");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
