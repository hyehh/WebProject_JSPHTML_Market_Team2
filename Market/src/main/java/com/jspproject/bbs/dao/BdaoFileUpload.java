package com.jspproject.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BdaoFileUpload {

	DataSource dataSource;

	public BdaoFileUpload() {
		System.out.println("Connectiong database...");
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/market");
			System.out.println("Database connection success");
		} catch (NamingException e) {
			System.out.println("Database connection failed");
			e.printStackTrace();
		}
	}
	
	
	
	//DB에 정보를 입력. 모든값이 null 허용입니다. 실제구현시엔 jsp에서 null값을 체크해서 꼭 필요한 값은 넣게끔 해야합니다.
		public void write(String pFilePath) {

			String query = "INSERT INTO Product (pFilePath) VALUES (?)";

			Connection conn = null;
			PreparedStatement psmt = null;

			try {
				conn = dataSource.getConnection();
				psmt = conn.prepareStatement(query);

//				psmt.setString(1, title);
//				psmt.setString(2, writer);
//				psmt.setString(3, content);
				psmt.setString(1, pFilePath);

				psmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("<data insert Fail>");
				e.printStackTrace();
			} finally {
				try {
					if (psmt != null)
						psmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					System.out.println("< psmt, conn close Fail>");
					e.printStackTrace();
				}
			}
		}
}
