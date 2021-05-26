package com.jspproject.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jspproject.bbs.dto.BDtoProductList;

public class BDaoCategory {

	DataSource dataSource;
	
	public BDaoCategory() {
		try {
			Context context = new InitialContext();	// context.xml 연결 -  InitialContext() 초기화 시킨다
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Market");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<BDtoProductList> categoryList(String pCategory){
		ArrayList<BDtoProductList> dtos = new ArrayList<BDtoProductList>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select p.pCode, p.pName, p.pExpirationDate, p.pPriceDC, 100-p.pPriceDC/p.pPrice*100, "
					+ "sum(b.bReviewScore)/count(b.bReviewScore), count(b.bReviewWriteDate), p.pFilePath "
					+ "from Product as p, BnS as b "
					+ "where p.pCode=b.Product_pCode and p.pCategory=? and p.pDeleteDate is null and b.bReviewDeleteDate is null "
					+ "group by p.pCode";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, pCategory);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int pCode = resultSet.getInt("pCode");
				String pName = resultSet.getString("pName");
				String pExpirationDate = resultSet.getString("pExpirationDate");
				int pPriceDC = resultSet.getInt("pPriceDC");
				int pDiscount = resultSet.getInt(5);
				String star = String.format("%.1f", resultSet.getDouble(6));
				int pCount = resultSet.getInt(7);
				String pFilePath = resultSet.getString("pFilePath");
				
				BDtoProductList dto = new BDtoProductList(pCode, pName, pExpirationDate, pPriceDC, pDiscount, star, pCount, pFilePath);
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
