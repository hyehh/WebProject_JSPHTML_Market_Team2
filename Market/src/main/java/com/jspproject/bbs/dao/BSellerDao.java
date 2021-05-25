package com.jspproject.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jspproject.bbs.dto.BSellerDto;

public class BSellerDao { // 2021.05.18 조혜지 - 회원 탈퇴하는 method
						  // 2021.05.25 조혜지 - 판매자 비밀번호 변경하는 dao sellerPwUpdate, 판매자 정보 수정 db에 있는 정보 불러오는 dao sellerinfo, 판매자 정보 수정 버튼 클릭 시 정보 업데이트 dao sellerInfoUpdate
	
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
	
	// 2021.05.25 조혜지 - 판매자 비밀번호 변경하는 method
	public void sellerPwUpdate(String sPw, String sId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			// select 문장에 ? 적어주기!
			String queryA = "update seller set sPw = ? ";
			String queryB = "where sId = ?";
			preparedStatement = connection.prepareStatement(queryA + queryB);
			
			// 지금은 문자로 써도 상관없으나 정상적으로 setInt로 적어야 함
			preparedStatement.setString(1, sPw);
			preparedStatement.setString(2, sId);
			
			preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			// 마지막에 정리를 해줘야함 (웹이기 때문에!)
			try {
				// 제일 안쪽부터 정리 (rs -> ps -> connect)
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	// 2021.05.25 조혜지 - 판매자 정보 수정 db에 있는 정보 불러오는 method
	public BSellerDto sellerinfo(String strsId){
		BSellerDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ResultSet resultSet = null;
		
		String queryA = "select sId, sName, sEmail, sBirth, sTel, sPostalCode, sAddress1, sAddress2, sShopName, sNumber ";
		String queryB = "from seller where sId = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(queryA + queryB);
			
			preparedStatement.setString(1, strsId);

			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
								
				String cId = resultSet.getString("sId");
				String cName = resultSet.getString("sName");
				String cEmail = resultSet.getString("sEmail");
				String cBirth = resultSet.getString("sBirth");
				String cTel = resultSet.getString("sTel");
				String cPostalCode = resultSet.getString("sPostalCode");
				String cAddress1 = resultSet.getString("sAddress1");
				String cAddress2 = resultSet.getString("sAddress2");
				String sShopName = resultSet.getString("sShopName");
				String sNumber = resultSet.getString("sNumber");
			
				dto = new BSellerDto(cId, cName, cEmail, cBirth, cTel, cPostalCode, cAddress1, cAddress2, sShopName, sNumber);

			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return dto;
	}

	// 2021.05.25 조혜지 - 판매자 정보 수정 버튼 클릭 시 정보 업데이트 method
	public void sellerInfoUpdate(String sName, String sEmail, String sBirth, String sTel, String sPostalCode, String sAddress1, String sAddress2, String sShopName, String sNumber, String sId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			// select 문장에 ? 적어주기!
			String queryA = "update seller set sName = ?, sEmail = ?, sBirth = ?, sTel = ?, sPostalCode = ?, sAddress1 = ?, sAddress2 = ?, sShopName = ?, sNumber = ? ";
			String queryB = "where sId = ?";
			preparedStatement = connection.prepareStatement(queryA + queryB);
			
			// 지금은 문자로 써도 상관없으나 정상적으로 setInt로 적어야 함
			preparedStatement.setString(1, sName);
			preparedStatement.setString(2, sEmail);
			preparedStatement.setString(3, sBirth);
			preparedStatement.setString(4, sTel);
			preparedStatement.setString(5, sPostalCode);
			preparedStatement.setString(6, sAddress1);
			preparedStatement.setString(7, sAddress2);
			preparedStatement.setString(8, sShopName);
			preparedStatement.setString(9, sNumber);
			preparedStatement.setString(10, sId);
			
			preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			// 마지막에 정리를 해줘야함 (웹이기 때문에!)
			try {
				// 제일 안쪽부터 정리 (rs -> ps -> connect)
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
