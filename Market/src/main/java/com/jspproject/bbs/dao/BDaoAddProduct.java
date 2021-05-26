package com.jspproject.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BDaoAddProduct {

	DataSource dataSource;
	
	public BDaoAddProduct() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/market");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
//	String pCategory, String pName, String pPrice, String pExpirationDate, String pQuantity, String pStatus
	public void insert(String pCategory, String pName, String pPrice, String pExpirationDate, 
			String pProductEA, String pStatus, String pPriceDC, String pFilePath) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "insert into Product  (pCategory, pName, pPrice, pPriceDC, pExpirationDate, pProductEA, pStatus, "
					+ "pAddDate, pFilePath, pDelete) "
					+ "value (?, ?, ?, ?, ?, ?, ?, now(), ?, 0)";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, pCategory);
			preparedStatement.setString(2, pName);
			preparedStatement.setString(3, pPrice);
			preparedStatement.setString(4, pPriceDC);
			preparedStatement.setString(5, pExpirationDate);
			preparedStatement.setString(6, pProductEA);
			preparedStatement.setString(7, pStatus);
			preparedStatement.setString(8, pFilePath);
			
			preparedStatement.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) preparedStatement.close();
				if (connection != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getCode() {
		int pCode = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		System.out.println("겟코드 실행여부입니다");
		try {
			connection = dataSource.getConnection();

			String query = "select max(pCode) from Product";
			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				pCode = resultset.getInt("max(pCode)");
			}

			System.out.println("dao" + pCode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null)
					resultset.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return pCode;
	}

	public void insertBnS(int pCode, String sId) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();

			String query = "insert into BnS (product_pCode, customer_cId) "
					+ "value (?, ?)";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, pCode);
			preparedStatement.setString(2, sId);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
