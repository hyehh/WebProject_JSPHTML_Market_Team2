package com.jspproject.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jspproject.bbs.dto.BCustomerInfoDto;

public class BCustomerInfoDao { // 2021.05.13 조혜지 - 회원 정보 수정 db에 있는 정보 불러오는 dao customerinfo
								// 2021.05.15 조혜지 - 회원 정보 수정 버튼 클릭 시 정보 업데이트 dao customerInfoUpdate, 비밀번호 변경 dao customerPwUpdate, 회원 탈퇴 dao customerSignOut

	// Field
	DataSource dataSource;
	
	// Constructor
	public BCustomerInfoDao() {
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
	// 2021.05.13 조혜지 - 회원 정보 수정 db에 있는 정보 불러오는 method
	public BCustomerInfoDto customerinfo(String strcId){
		BCustomerInfoDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ResultSet resultSet = null;
		
		String queryA = "select cId, cName, cEmail, cBirth, cTel, cPostalCode, cAddress1, cAddress2 ";
		String queryB = "from customer where cId = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(queryA + queryB);
			
			preparedStatement.setString(1, strcId);

			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
								
				String cId = resultSet.getString("cId");
				String cName = resultSet.getString("cName");
				String cEmail = resultSet.getString("cEmail");
				String cBirth = resultSet.getString("cBirth");
				String cTel = resultSet.getString("cTel");
				String cPostalCode = resultSet.getString("cPostalCode");
				String cAddress1 = resultSet.getString("cAddress1");
				String cAddress2 = resultSet.getString("cAddress2");
			
				dto = new BCustomerInfoDto(cId, cName, cEmail, cBirth, cTel, cPostalCode, cAddress1, cAddress2, cAddress2);

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

	// 2021.05.15 조혜지 - 회원 정보 수정 버튼 클릭 시 정보 업데이트 method
	public void customerInfoUpdate(String cName, String cEmail, String cBirth, String cTel, String cPostalCode, String cAddress1, String cAddress2, String cId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			// select 문장에 ? 적어주기!
			String queryA = "update customer set cName = ?, cEmail = ?, cBirth = ?, cTel = ?, cPostalCode = ?, cAddress1 = ?, cAddress2 = ? ";
			String queryB = "where cId = ?";
			preparedStatement = connection.prepareStatement(queryA + queryB);
			
			// 지금은 문자로 써도 상관없으나 정상적으로 setInt로 적어야 함
			preparedStatement.setString(1, cName);
			preparedStatement.setString(2, cEmail);
			preparedStatement.setString(3, cBirth);
			preparedStatement.setString(4, cTel);
			preparedStatement.setString(5, cPostalCode);
			preparedStatement.setString(6, cAddress1);
			preparedStatement.setString(7, cAddress2);
			preparedStatement.setString(8, cId);
			
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
	
	// 2021.05.15 조혜지 - 비밀번호 변경하는 method
	public void customerPwUpdate(String cPw, String cId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			// select 문장에 ? 적어주기!
			String queryA = "update customer set cPw = ? ";
			String queryB = "where cId = ?";
			preparedStatement = connection.prepareStatement(queryA + queryB);
			
			// 지금은 문자로 써도 상관없으나 정상적으로 setInt로 적어야 함
			preparedStatement.setString(1, cPw);
			preparedStatement.setString(2, cId);
			
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
	
	// 2021.05.15 조혜지 - 회원 탈퇴하는 method
	public void customerSignOut(String[] cSignOutReason, String cSignOutContent, String cId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String queryA = "update customer set cSignOutReason = ?, cSignOutContent = ?, cDropDate = curdate() ";
			String queryB = "where cId = ?";
			
			String ReasonList = "";
			for(String s : cSignOutReason) {
				ReasonList += " - " + s;
			}
			
			preparedStatement = connection.prepareStatement(queryA + queryB);
			
			preparedStatement.setString(1, ReasonList);
			preparedStatement.setString(2, cSignOutContent);
			preparedStatement.setString(3, cId);
			
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
