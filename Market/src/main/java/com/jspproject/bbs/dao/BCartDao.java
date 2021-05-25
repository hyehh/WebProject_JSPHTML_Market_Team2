package com.jspproject.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jspproject.bbs.dto.BCartDto;

public class BCartDao { // 2021.05.16 조혜지 - 장바구니 버튼 클릭시 DB에 insert하는 dao insert, 장바구니 목록 DB에서 불러오는 dao customerCartList
					    // 2021.05.18 조혜지 - 장바구니 전체 상품 삭제하는 dao cartAllDelete
						// 2021.05.22 조혜지 - 장바구니 선택 상품 삭제하는 dao cartDelete
						// 2021.05.23 조혜지 - 장바구니에 같은 제품이 존재하는지 여부 체크하는 dao cartCheck, 장바구니 페이지 분할을 위해 몇 줄인지 세는 dao cart
						// 2021.05.24 조혜지 - 장바구니 버튼 클릭시 이미 있는 제품인 경우 DB에 수량 update하는 dao update
	
	// Field
	DataSource dataSource;
	
	// Constructor
	public BCartDao() {
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
	// 2021.05.23 조혜지 - 장바구니에 같은 제품이 존재하는지 여부 체크하는 method
	public int cartCheck(String cId, int pCode, int cart) {
		//boolean check = true;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			
			String query = "select Product_pCode from WishList where Customer_cId = ? and Product_pCode = ?";
			preparedStatement = connection.prepareStatement(query);
			
			// 위에에서 파라미터로 준 bName
			preparedStatement.setString(1, cId);
			preparedStatement.setInt(2, pCode);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()){
				cart = 1;	//아이디 중복
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			// 마지막에 정리를 해줘야함 (웹이기 때문에!)
			try {
				// 제일 안쪽부터 정리 (ps -> connect)
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return cart;
	}
	
	// 2021.05.16 조혜지 - 장바구니 버튼 클릭시 DB에 insert하는 method
	public void insert(int wQuantity, String cId, int pCode) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();
			
			String query = "insert into WishList (wDate, wQuantity, Customer_cId, Product_pCode) values (now(),?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			
			// 위에에서 파라미터로 준 bName
			preparedStatement.setInt(1, wQuantity);
			preparedStatement.setString(2, cId);
			preparedStatement.setInt(3, pCode);
			
			preparedStatement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			// 마지막에 정리를 해줘야함 (웹이기 때문에!)
			try {
				// 제일 안쪽부터 정리 (ps -> connect)
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	// 2021.05.24 조혜지 - 장바구니 버튼 클릭시 이미 있는 제품인 경우 DB에 수량 update하는 method
	public void update(int wQuantity, String cId, int pCode) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "update WishList set wQuantity = ? where Customer_cId = ? and Product_pCode = ?";
			preparedStatement = connection.prepareStatement(query);
			
			// 위에에서 파라미터로 준 bName
			preparedStatement.setInt(1, wQuantity);
			preparedStatement.setString(2, cId);
			preparedStatement.setInt(3, pCode);
			
			preparedStatement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			// 마지막에 정리를 해줘야함 (웹이기 때문에!)
			try {
				// 제일 안쪽부터 정리 (ps -> connect)
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	// 2021.05.16 조혜지 - 장바구니 목록 DB에서 불러오는 method
	public ArrayList<BCartDto> customerCartList(String strcId, int from, int to){
		ArrayList<BCartDto> dtos = new ArrayList<BCartDto>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String queryA = "select p.pName, w.wQuantity, p.pExpirationDate, p.pPriceDC, w.wId, p.pCode, p.pProductEA from WishList as w, Product as p, Customer as c ";
			String queryB = "where w.Customer_cId = c.cId and w.Product_pCode = p.pCode and c.cId = ? limit ?, ?";
			preparedStatement = connection.prepareStatement(queryA + queryB);
			
			preparedStatement.setString(1, strcId);
			preparedStatement.setInt(2, from);
			preparedStatement.setInt(3, to);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String pName = resultSet.getString("pName");
				int wQuantity = resultSet.getInt("wQuantity");
				String pExpirationDate = resultSet.getString("pExpirationDate");
				int pPriceDC = resultSet.getInt("pPriceDC");
				int wId = resultSet.getInt("wId");
				int pCode = resultSet.getInt("pCode");
				String pProductEA = resultSet.getString("pProductEA");
				
				BCartDto dto = new BCartDto(pName, wQuantity, pExpirationDate, pPriceDC, wId, pCode, pProductEA);
				dtos.add(dto);
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
		return dtos;
	}
	
	// 2021.05.18 조혜지 - 장바구니 전체 상품 삭제하는 method
	public void cartAllDelete(String cId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "delete from WishList where Customer_cId = ?";
			preparedStatement = connection.prepareStatement(query);
			
			// 위에에서 파라미터로 준 bName
			preparedStatement.setString(1, cId);
			
			preparedStatement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			// 마지막에 정리를 해줘야함 (웹이기 때문에!)
			try {
				// 제일 안쪽부터 정리 (ps -> connect)
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	// 2021.05.22 조혜지 - 장바구니 선택 상품 삭제하는 method
	public void cartDelete(String cId, int wId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "delete from WishList where Customer_cId = ? and wId = ?";
			preparedStatement = connection.prepareStatement(query);
			
			// 위에에서 파라미터로 준 bName
			preparedStatement.setString(1, cId);
			preparedStatement.setInt(2, wId);
			
			preparedStatement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			// 마지막에 정리를 해줘야함 (웹이기 때문에!)
			try {
				// 제일 안쪽부터 정리 (ps -> connect)
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	// 2021.05.23 조혜지 - 장바구니 페이지 분할을 위해 몇 줄인지 세는 method
	public int cart(String strcId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int orderCount = 0;
		
		try {
			connection = dataSource.getConnection();
			
			String queryA = "select p.pName, w.wQuantity, p.pExpirationDate, p.pPriceDC, w.wId, p.pCode, p.pProductEA from WishList as w, Product as p, Customer as c ";
			String queryB = "where w.Customer_cId = c.cId and w.Product_pCode = p.pCode and c.cId = ?";
			
			preparedStatement = connection.prepareStatement(queryA + queryB);
			preparedStatement.setString(1, strcId);

			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				orderCount++;
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null) resultset.close();
				if (preparedStatement != null) preparedStatement.close();
				if (connection != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return orderCount;
		
	}
	
	
}
