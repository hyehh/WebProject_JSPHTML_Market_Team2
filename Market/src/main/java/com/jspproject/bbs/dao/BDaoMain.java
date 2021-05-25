package com.jspproject.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jspproject.bbs.dto.BDtoBest;
import com.jspproject.bbs.dto.BDtoProductList;

public class BDaoMain {
	
	DataSource dataSource;
	
	public BDaoMain() {
		try {
			Context context = new InitialContext();	// context.xml 연결 -  InitialContext() 초기화 시킨다
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Market");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	// 베스트 4개
	public ArrayList<BDtoBest> mainBest(){
		ArrayList<BDtoBest> dtos = new ArrayList<BDtoBest>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select p.pCode, sum(b.bQuantity), p.pName, p.pPriceDC, p.pPriceDC/p.pPrice*100, p.pMainFilePath "
							+ "from BnS as b, Product as p "
							+ "where b.Product_pCode=p.pCode and p.pDeleteDate is null and b.bBuyCancelDate is null "
							+ "group by b.Product_pCode order by sum(b.bQuantity) desc limit 4";
			preparedStatement = connection.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int pCode = resultSet.getInt("pCode");
				int pCount = resultSet.getInt(2);
				String pName = resultSet.getString("pName");
				int pPriceDC = resultSet.getInt("pPriceDC");
				int pDiscount = resultSet.getInt(5);
				String pMainFilePath = resultSet.getString("pMainFilePath");
				
				BDtoBest dto = new BDtoBest(pCode, pCount, pName, pPriceDC, pDiscount, pMainFilePath);
				
				dtos.add(dto);	// ArrayList dtos에 dto 한줄씩 넣기
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {	// 잘 작동되어도 에러걸려도 무조건 finally로 모임
			try {
				if(resultSet != null) resultSet.close();					// resultSet에 값이 있으면 끝내라
				if(preparedStatement != null) preparedStatement.close();	// preparedStatement에 값이 있으면 끝내라
				if(connection != null) connection.close();					// connection에 값이 있으면 끝내라
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
	// new 4개
	public ArrayList<BDtoProductList> mainNew(){
		ArrayList<BDtoProductList> dtos = new ArrayList<BDtoProductList>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select p.pCode, p.pName, p.pExpirationDate, p.pPriceDC, p.pPriceDC/p.pPrice*100, "
							+ "sum(b.bReviewScore)/count(b.bReviewScore), count(b.bReviewWriteDate), p.pMainFilePath "
							+ "from Product as p, BnS as b "
							+ "where p.pCode=b.Product_pCode and p.pDeleteDate is null and b.bReviewDeleteDate is null "
							+ "group by p.pCode order by p.pAddDate desc limit 4";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int pCode = resultSet.getInt("pCode");
				String pName = resultSet.getString("pName");
				String pExpirationDate = resultSet.getString("pExpirationDate");
				int pPriceDC = resultSet.getInt("pPriceDC");
				int pDiscount = resultSet.getInt(5);
				String star = String.format("%.1f", resultSet.getDouble(6));
				int pCount = resultSet.getInt(7);
				String pMainFilePath = resultSet.getString("pMainFilePath");
				
				BDtoProductList dto = new BDtoProductList(pCode, pName, pExpirationDate, pPriceDC, pDiscount, star, pCount, pMainFilePath);
				dtos.add(dto);	// ArrayList dtos에 dto 한줄씩 넣기
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {	// 잘 작동되어도 에러걸려도 무조건 finally로 모임
			try {
				if(resultSet != null) resultSet.close();					// resultSet에 값이 있으면 끝내라
				if(preparedStatement != null) preparedStatement.close();	// preparedStatement에 값이 있으면 끝내라
				if(connection != null) connection.close();					// connection에 값이 있으면 끝내라
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
	// 랜덤 20개
	public ArrayList<BDtoProductList> mainRand(){
		ArrayList<BDtoProductList> dtos = new ArrayList<BDtoProductList>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select p.pCode, p.pName, p.pExpirationDate, p.pPriceDC, p.pPriceDC/p.pPrice*100, "
							+ "sum(b.bReviewScore)/count(b.bReviewScore), count(b.bReviewWriteDate), p.pMainFilePath "
							+ "from Product as p, BnS as b "
							+ "where p.pCode=b.Product_pCode and p.pDeleteDate is null and b.bReviewDeleteDate is null "
							+ "group by p.pCode order by rand() limit 20";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int pCode = resultSet.getInt("pCode");
				String pName = resultSet.getString("pName");
				String pExpirationDate = resultSet.getString("pExpirationDate");
				int pPriceDC = resultSet.getInt("pPriceDC");
				int pDiscount = resultSet.getInt(5);
				String star = String.format("%.1f", resultSet.getDouble(6));
				int pCount = resultSet.getInt(7);
				String pMainFilePath = resultSet.getString("pMainFilePath");
				
				BDtoProductList dto = new BDtoProductList(pCode, pName, pExpirationDate, pPriceDC, pDiscount, star, pCount, pMainFilePath);
				dtos.add(dto);	// ArrayList dtos에 dto 한줄씩 넣기
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {	// 잘 작동되어도 에러걸려도 무조건 finally로 모임
			try {
				if(resultSet != null) resultSet.close();					// resultSet에 값이 있으면 끝내라
				if(preparedStatement != null) preparedStatement.close();	// preparedStatement에 값이 있으면 끝내라
				if(connection != null) connection.close();					// connection에 값이 있으면 끝내라
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
}
