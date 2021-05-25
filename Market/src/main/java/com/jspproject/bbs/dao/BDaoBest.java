package com.jspproject.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jspproject.bbs.dto.BDtoBest;

public class BDaoBest {
	
	DataSource dataSource;
	
	public BDaoBest() {
		try {
			Context context = new InitialContext();	// context.xml 연결 -  InitialContext() 초기화 시킨다
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Market");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<BDtoBest> kitchen(){
		ArrayList<BDtoBest> dtos = new ArrayList<BDtoBest>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select p.pCode, sum(b.bQuantity), p.pName, p.pPriceDC, p.pPriceDC/p.pPrice*100, p.pMainFilePath "
							+ "from BnS as b, Product as p "
							+ "where b.Product_pCode=p.pCode and p.pCategory=? and p.pDeleteDate is null and b.bBuyCancelDate is null "
							+ "group by b.Product_pCode order by sum(b.bQuantity) desc limit 4";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "주방용품");
			
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
	
	public ArrayList<BDtoBest> washing(){
		ArrayList<BDtoBest> dtos = new ArrayList<BDtoBest>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select p.pCode, sum(b.bQuantity), p.pName, p.pPriceDC, p.pPriceDC/p.pPrice*100, p.pMainFilePath "
							+ "from BnS as b, Product as p "
							+ "where b.Product_pCode=p.pCode and p.pCategory=? and p.pDeleteDate is null and b.bBuyCancelDate is null "
							+ "group by b.Product_pCode order by sum(b.bQuantity) desc limit 4";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "세탁용품");
			
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
	
	public ArrayList<BDtoBest> cleaning(){
		ArrayList<BDtoBest> dtos = new ArrayList<BDtoBest>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select p.pCode, sum(b.bQuantity), p.pName, p.pPriceDC, p.pPriceDC/p.pPrice*100, p.pMainFilePath "
							+ "from BnS as b, Product as p "
							+ "where b.Product_pCode=p.pCode and p.pCategory=? and p.pDeleteDate is null and b.bBuyCancelDate is null "
							+ "group by b.Product_pCode order by sum(b.bQuantity) desc limit 4";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "청소용품");
			
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
	
	public ArrayList<BDtoBest> interior(){
		ArrayList<BDtoBest> dtos = new ArrayList<BDtoBest>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select p.pCode, sum(b.bQuantity), p.pName, p.pPriceDC, p.pPriceDC/p.pPrice*100, p.pMainFilePath "
							+ "from BnS as b, Product as p "
							+ "where b.Product_pCode=p.pCode and p.pCategory=? and p.pDeleteDate is null and b.bBuyCancelDate is null "
							+ "group by b.Product_pCode order by sum(b.bQuantity) desc limit 4";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "인테리어소품");
			
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

	public ArrayList<BDtoBest> can(){
		ArrayList<BDtoBest> dtos = new ArrayList<BDtoBest>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select p.pCode, sum(b.bQuantity), p.pName, p.pPriceDC, p.pPriceDC/p.pPrice*100, p.pMainFilePath "
							+ "from BnS as b, Product as p "
							+ "where b.Product_pCode=p.pCode and p.pCategory=? and p.pDeleteDate is null and b.bBuyCancelDate is null "
							+ "group by b.Product_pCode order by sum(b.bQuantity) desc limit 4";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "통조림");
			
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
	
	public ArrayList<BDtoBest> frozen(){
		ArrayList<BDtoBest> dtos = new ArrayList<BDtoBest>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select p.pCode, sum(b.bQuantity), p.pName, p.pPriceDC, p.pPriceDC/p.pPrice*100, p.pMainFilePath "
							+ "from BnS as b, Product as p "
							+ "where b.Product_pCode=p.pCode and p.pCategory=? and p.pDeleteDate is null and b.bBuyCancelDate is null "
							+ "group by b.Product_pCode order by sum(b.bQuantity) desc limit 4";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "냉동식품");
			
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

	public ArrayList<BDtoBest> beverage(){
		ArrayList<BDtoBest> dtos = new ArrayList<BDtoBest>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select p.pCode, sum(b.bQuantity), p.pName, p.pPriceDC, p.pPriceDC/p.pPrice*100, p.pMainFilePath "
							+ "from BnS as b, Product as p "
							+ "where b.Product_pCode=p.pCode and p.pCategory=? and p.pDeleteDate is null and b.bBuyCancelDate is null "
							+ "group by b.Product_pCode order by sum(b.bQuantity) desc limit 4";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "식음료");
			
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
	
}
