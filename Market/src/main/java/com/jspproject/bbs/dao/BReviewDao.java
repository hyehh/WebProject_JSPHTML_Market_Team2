package com.jspproject.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jspproject.bbs.dto.BReviewDto;

public class BReviewDao { // 2021.05.16 조혜지 - 리뷰 미작성 목록 불러오는 dao reviewRegistrationList
						  // 2021.05.17 조혜지 - 리뷰 등록하는 dao reviewRegistration
  						  // 2021.05.18 조혜지 - 리뷰 작성된 목록 불러오는 dao reviewDeleteList, 리뷰 삭제하는 dao reviewDelete
						  // 2021.05.23 조혜지 - 리뷰등록 페이지 분할을 위해 몇 줄인지 세는 dao reviewreg, 리뷰삭제 페이지 분할을 위해 몇 줄인지 세는 dao reviewdel

	// Field
	DataSource dataSource;
	
	// Constructor
	public BReviewDao() {
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
	// 2021.05.16 조혜지 - 리뷰 미작성 목록 불러오는 method
	public ArrayList<BReviewDto> reviewRegistrationList(String strcId, int from, int to){
		ArrayList<BReviewDto> dtos = new ArrayList<BReviewDto>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String queryA = "select b.bNumber, b.bBuyDate, p.pName, b.bQuantity, p.pPriceDC, p.pCode from BnS as b, Product as p, Customer as c, Delivery as d ";
			String queryB =	"where b.Customer_cId = c.cId and p.pCode = b.Product_pCode and d.Product_pCode = p.pCode and d.Customer_cId = c.cId and d.Product_pCode = b.Product_pCode and d.Customer_cId = b.Customer_cId and d.bNumber = b.bNumber and ";
			String queryC =	"b.bReviewScore is null and b.bBuyCancelDate is null and d.dEndDate is not null and c.cId = ? order by b.bSeq desc limit ?, ?";
//			String queryA = "select b.bNumber, b.bBuyDate, p.pName, b.bQuantity, p.pPriceDC, p.pCode from BnS as b, Product as p, Customer as c ";
//			String queryB =	"where b.Customer_cId = c.cId and p.pCode = b.Product_pCode and b.bReviewScore is null and b.bBuyCancelDate is null and c.cId = ? order by b.bSeq desc limit ?, ?";
			
			preparedStatement = connection.prepareStatement(queryA + queryB + queryC);
			
			preparedStatement.setString(1, strcId);
			preparedStatement.setInt(2, from);
			preparedStatement.setInt(3, to);

			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String bNumber = resultSet.getString("bNumber");
				String bBuyDate = resultSet.getString("bBuyDate");
				String pName = resultSet.getString("pName");
				int bQuantity = resultSet.getInt("bQuantity");
				int pPriceDC = resultSet.getInt("pPriceDC");
				int pCode = resultSet.getInt("pCode");
				
				BReviewDto dto = new BReviewDto(bNumber, bBuyDate, pName, bQuantity, pPriceDC, pCode);
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

	// 2021.05.17 조혜지 - 리뷰 등록하는 method
	public void reviewRegistration(int bReviewScore, String bReviewContent, String bNumber, int pCode, String rFilePath) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			// select 문장에 ? 적어주기!
			String queryA = "update BnS as b, Product as p set b.rFilePath = ?, b.bReviewScore = ?, b.bReviewContent = ?, b.bReviewWriteDate = curdate() ";
			String queryB = "where b.Product_pCode = p.pCode and b.bNumber = ? and p.pCode = ?";
			preparedStatement = connection.prepareStatement(queryA + queryB);
			
			preparedStatement.setString(1, rFilePath);
			preparedStatement.setInt(2, bReviewScore);
			preparedStatement.setString(3, bReviewContent);
			preparedStatement.setString(4, bNumber);
			preparedStatement.setInt(5, pCode);
			
			preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("<data insert Fail>");
			e.printStackTrace();
		}finally {
			// 마지막에 정리를 해줘야함 (웹이기 때문에!)
			try {
				// 제일 안쪽부터 정리 (rs -> ps -> connect)
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("< psmt, conn close Fail>");
				e.printStackTrace();
			}
		}
	}
	
	// 2021.05.18 조혜지 - 리뷰 작성된 목록 불러오는 method
	public ArrayList<BReviewDto> reviewDeleteList(String strcId, int from, int to){
		ArrayList<BReviewDto> dtos = new ArrayList<BReviewDto>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String queryA = "select b.bNumber, b.bBuyDate, p.pName, p.pCode, b.bReviewScore, b.bReviewContent from BnS as b, Product as p, Customer as c ";
			String queryB =	"where b.Customer_cId = c.cId and p.pCode = b.Product_pCode and b.bReviewScore is not null and c.cId = ? order by b.bSeq desc limit ?, ?";
			
			preparedStatement = connection.prepareStatement(queryA + queryB);
			
			preparedStatement.setString(1, strcId);
			preparedStatement.setInt(2, from);
			preparedStatement.setInt(3, to);

			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String bNumber = resultSet.getString("bNumber");
				String bBuyDate = resultSet.getString("bBuyDate");
				String pName = resultSet.getString("pName");
				int pCode = resultSet.getInt("pCode");
				int bReviewScore = resultSet.getInt("bReviewScore");
				String bReviewContent = resultSet.getString("bReviewContent");
				
				BReviewDto dto = new BReviewDto(bNumber, bBuyDate, pName, pCode, bReviewScore, bReviewContent);
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

	// 2021.05.18 조혜지 - 리뷰 삭제하는 method
	public void reviewDelete(String strbNumber, int intpCode) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			// select 문장에 ? 적어주기!
			String queryA = "update BnS as b, Product as p set b.bReviewDeleteDate = curdate(), b.bReviewContent = null, b.bReviewScore = null, b.bReviewWriteDate = null ";
			String queryB = "where b.Product_pCode = p.pCode and b.bNumber = ? and p.pCode = ?";
			preparedStatement = connection.prepareStatement(queryA + queryB);
			
			// 지금은 문자로 써도 상관없으나 정상적으로 setInt로 적어야 함
			preparedStatement.setString(1, strbNumber);
			preparedStatement.setInt(2, intpCode);
			
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
	
	// 2021.05.23 조혜지 - 리뷰등록 페이지 분할을 위해 몇 줄인지 세는 dao reviewreg
	public int reviewreg(String strcId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int reviewRegCount = 0;
		
		try {
			connection = dataSource.getConnection();
			
			String queryA = "select b.bNumber, b.bBuyDate, p.pName, b.bQuantity, p.pPriceDC, p.pCode from BnS as b, Product as p, Customer as c, Delivery as d ";
			String queryB =	"where b.Customer_cId = c.cId and p.pCode = b.Product_pCode and d.Product_pCode = p.pCode and d.Customer_cId = c.cId and d.Product_pCode = b.Product_pCode and d.Customer_cId = b.Customer_cId and d.bNumber = b.bNumber and ";
			String queryC =	"b.bReviewScore is null and b.bBuyCancelDate is null and d.dEndDate is not null and c.cId = ? order by b.bSeq desc";
//			String queryA = "select b.bNumber, b.bBuyDate, p.pName, b.bQuantity, p.pPriceDC, p.pCode from BnS as b, Product as p, Customer as c ";
//			String queryB =	"where b.Customer_cId = c.cId and p.pCode = b.Product_pCode and b.bReviewScore is null and b.bBuyCancelDate is null and c.cId = ? order by b.bSeq desc limit ?, ?";
			
			preparedStatement = connection.prepareStatement(queryA + queryB + queryC);
//			String queryA = "select b.bNumber, b.bBuyDate, p.pName, b.bQuantity, p.pPriceDC, p.pCode from BnS as b, Product as p, Customer as c ";
//			String queryB =	"where b.Customer_cId = c.cId and p.pCode = b.Product_pCode and b.bReviewScore is null and c.cId = ? order by b.bSeq desc";		
			
//			preparedStatement = connection.prepareStatement(queryA + queryB);
			preparedStatement.setString(1, strcId);

			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				reviewRegCount++;
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
		return reviewRegCount;
	}
	
	// 2021.05.23 조혜지 - 리뷰삭제 페이지 분할을 위해 몇 줄인지 세는 dao reviewdel
	public int reviewdel(String strcId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int reviewDelCount = 0;
		
		try {
			connection = dataSource.getConnection();
			
			String queryA = "select b.bNumber, b.bBuyDate, p.pName, p.pCode, b.bReviewScore, b.bReviewContent from BnS as b, Product as p, Customer as c ";
			String queryB =	"where b.Customer_cId = c.cId and p.pCode = b.Product_pCode and b.bReviewScore is not null and c.cId = ? order by b.bSeq desc";
		
			preparedStatement = connection.prepareStatement(queryA + queryB);
			preparedStatement.setString(1, strcId);

			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				reviewDelCount++;
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
		return reviewDelCount;
	}
	
}
