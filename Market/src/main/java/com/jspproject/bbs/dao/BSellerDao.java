package com.jspproject.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BSellerDao { // 2021.05.18 조혜지 - 회원 탈퇴하는 method
	
	// Field
	DataSource dataSource;
	
	// Constructor
	public BSellerDao() {
		// TODO Auto-generated constructor stub
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Market");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// Method
	// 2021.05.18 조혜지 - 회원 탈퇴하는 method
	public void sellerSignOut(String[] sSignOutReason, String sSignOutContent, String sId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String queryA = "update seller set sSignOutReason = ?, sSignOutContent = ?, sDropDate = curdate() ";
			String queryB = "where sId = ?";
			
			String ReasonList = "";
			for(String s : sSignOutReason) {
				ReasonList += " - " + s;
			}
			
			preparedStatement = connection.prepareStatement(queryA + queryB);
			
			preparedStatement.setString(1, ReasonList);
			preparedStatement.setString(2, sSignOutContent);
			preparedStatement.setString(3, sId);
			
			preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
