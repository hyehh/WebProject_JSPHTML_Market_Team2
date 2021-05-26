package com.jspproject.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jspproject.bbs.dto.BDtoProduct;
import com.jspproject.bbs.dto.BDtoProductList;
import com.jspproject.bbs.dto.BDtoQnA;
import com.jspproject.bbs.dto.BDtoReview;

public class BDao {
	
	DataSource dataSource;
	
	public BDao() {
		try {
			Context context = new InitialContext();	// context.xml 연결 -  InitialContext() 초기화 시킨다
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Market");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 제품 상세페이지
	public BDtoProduct productView(String strCode) {
		BDtoProduct dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select p.pCode, p.pName, p.pCategory, p.pPrice, p.pPriceDC, 100-p.pPriceDC/pPrice*100, p.pExpirationDate, p.pProductEA, sum(b.bQuantity), p.pFilePath "
							+ "from Product as p, BnS as b where p.pCode= ? and p.pCode=b.Product_pCode and p.pDeleteDate is null";
			preparedStatement = connection.prepareStatement(query);
			
			// select 가기 전에 where ?값 해결해주고 가기
			preparedStatement.setInt(1, Integer.parseInt(strCode));
			
			resultSet = preparedStatement.executeQuery();
			
			// 값이 하나라서 한번 도니까 while 말고 if문으로 작성
			if(resultSet.next()) {
				int pCode = resultSet.getInt("pCode");
				String pName = resultSet.getString("pName");
				String pCategory = resultSet.getString("pCategory");
				int pPrice = resultSet.getInt("pPrice");
				int pPriceDC = resultSet.getInt("pPriceDC");
				int pDiscount = resultSet.getInt(6);
				String pExpirationDate = resultSet.getString("pExpirationDate");
				int pNowEA = resultSet.getInt("pProductEA");
				int pNowSell = resultSet.getInt(9);
				int pProductEA = pNowEA - pNowSell;
				String pFilePath = resultSet.getString("pFilePath");
				
				dto = new BDtoProduct(pCode, pName, pCategory, pPrice, pPriceDC, pDiscount, pExpirationDate, pProductEA, pFilePath);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	// 문의 등록
	public void registerQ(int pCode, String cId, String qTitle, String qContent, String qFilePath) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "insert into QnA(Product_pCode, Customer_cId, qTitle, qContent, qAddDate, Seller_sId, qFilePath) "
							+ "values(?, ?, ?, ?, now(), ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, pCode);
			preparedStatement.setString(2, cId);
			preparedStatement.setString(3, qTitle);
			preparedStatement.setString(4, qContent);
			preparedStatement.setString(5, "admin");
			preparedStatement.setString(6, qFilePath);
			
			preparedStatement.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {	// 잘 작동되어도 에러걸려도 무조건 finally로 모임
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 문의 list 보여주기
	public ArrayList<BDtoQnA> qnaList(String strCode, int from, int to){
		ArrayList<BDtoQnA> dtos = new ArrayList<BDtoQnA>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select QnACode, Customer_cId, qTitle, qContent, qAddDate, aAddDate, aContent, qFilePath "
							+ "from QnA where Product_pCode=? and aDeleteDate is null and qDeleteDate is null limit ?, ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strCode));
			preparedStatement.setInt(2, from);
			preparedStatement.setInt(3, to);
			
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int qnaCode = resultSet.getInt("QnACode");
				String customer_cId = resultSet.getString("Customer_cId");
				String qTitle = resultSet.getString("qTitle");
				String qContent = resultSet.getString("qContent");
				String qAddDate = resultSet.getString("qAddDate");
				String aAddDate = resultSet.getString("aAddDate");
				String aContent = resultSet.getString("aContent");
				String qFilePath = resultSet.getString("qFilePath");
				
				String answer = "";
				if(aAddDate == null) {
					answer = "답변전";
				}else {
					answer = "답변완료";
				}
				
				BDtoQnA dto = new BDtoQnA(qnaCode, customer_cId, qTitle, qContent, qAddDate, aAddDate, aContent, answer, qFilePath);
				
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
	
	// 전체 문의의 갯수
	public int qnaListCount(String strCode) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int count = 0;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select count(QnACode) from QnA where Product_pCode=? and qDeleteDate is null";
			preparedStatement = connection.prepareStatement(query);
			// select 가기 전에 where ?값 해결해주고 가기
			preparedStatement.setInt(1, Integer.parseInt(strCode));
			
			resultSet = preparedStatement.executeQuery();
			
			// 값이 하나라서 한번 도니까 while 말고 if문으로 작성
			if(resultSet.next()) {
				count = resultSet.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	
	// 리뷰 list 보여주기
	public ArrayList<BDtoReview> reviewList(String strCode, int from, int to){
		ArrayList<BDtoReview> dtos = new ArrayList<BDtoReview>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select bReviewContent, bReviewScore, bReviewWriteDate, Customer_cId, rFilePath "
							+ "from BnS where Product_pCode=? and bReviewDeleteDate is null and bReviewWriteDate is not null  limit ?, ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strCode));
			preparedStatement.setInt(2, from);
			preparedStatement.setInt(3, to);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String bReviewContent = resultSet.getString("bReviewContent");
				int bReviewScore = resultSet.getInt("bReviewScore");
				String bReviewWriteDate = resultSet.getString("bReviewWriteDate");
				String customer_cId = resultSet.getString("Customer_cId");
				String rFilePath = resultSet.getString("rFilePath");
				
				BDtoReview dto = new BDtoReview(bReviewContent, bReviewScore, bReviewWriteDate, customer_cId, rFilePath);
				
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
	
	// 전체 리뷰 갯수
	public int reivewListCount(String strCode) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int count = 0;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select count(bReviewWriteDate) from BnS where Product_pCode=? and bReviewDeleteDate is null";
			preparedStatement = connection.prepareStatement(query);
			// select 가기 전에 where ?값 해결해주고 가기
			preparedStatement.setInt(1, Integer.parseInt(strCode));
			
			resultSet = preparedStatement.executeQuery();
			
			// 값이 하나라서 한번 도니까 while 말고 if문으로 작성
			if(resultSet.next()) {
				count = resultSet.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
		
	}
	
	// 제품 평균 별점 불러오기
	public BDtoReview reviewStar(String strCode) {
		BDtoReview dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select sum(bReviewScore)/count(bReviewScore), count(bReviewScore) "
							+ "from BnS where Product_pCode=? and bReviewDeleteDate is null";
			preparedStatement = connection.prepareStatement(query);
			
			// select 가기 전에 where ?값 해결해주고 가기
			preparedStatement.setInt(1, Integer.parseInt(strCode));
			
			resultSet = preparedStatement.executeQuery();
			
			// 값이 하나라서 한번 도니까 while 말고 if문으로 작성
			if(resultSet.next()) {
				String star = String.format("%.1f", resultSet.getDouble(1));
				int count = resultSet.getInt(2);
				
				dto = new BDtoReview(star, count);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	// 마감임박 상품 리스트
	public ArrayList<BDtoProductList> deadlineList(){
		ArrayList<BDtoProductList> dtos = new ArrayList<BDtoProductList>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select p.pCode, p.pName, p.pExpirationDate, p.pPriceDC, 100-p.pPriceDC/p.pPrice*100, "
							+ "sum(b.bReviewScore)/count(b.bReviewScore), count(b.bReviewWriteDate), p.pFilePath "
							+ "from Product as p, BnS as b "
							+ "where p.pCode=b.Product_pCode and p.pDeleteDate is null and b.bReviewDeleteDate is null "
							+ "and p.pExpirationDate between now() and date_add(now(),interval 1 week ) "
							+ "group by p.pCode order by p.pExpirationDate";
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
	
	// new 상품 리스트
	public ArrayList<BDtoProductList> newList(){
		ArrayList<BDtoProductList> dtos = new ArrayList<BDtoProductList>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select p.pCode, p.pName, p.pExpirationDate, p.pPriceDC, 100-p.pPriceDC/p.pPrice*100, "
							+ "sum(b.bReviewScore)/count(b.bReviewScore), count(b.bReviewWriteDate), pFilePath "
							+ "from Product as p, BnS as b "
							+ "where p.pCode=b.Product_pCode and p.pDeleteDate is null and b.bReviewDeleteDate is null "
							+ "and p.pAddDate between date_sub(now(),interval 1 week ) and now() "
							+ "group by p.pCode order by p.pAddDate desc";
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
	
	// 검색
	public ArrayList<BDtoProductList> searchList(String searchName){
		ArrayList<BDtoProductList> dtos = new ArrayList<BDtoProductList>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select p.pCode, p.pName, p.pExpirationDate, p.pPriceDC, 100-p.pPriceDC/p.pPrice*100, "
							+ "sum(b.bReviewScore)/count(b.bReviewScore), count(b.bReviewWriteDate), pFilePath "
							+ "from Product as p, BnS as b "
							+ "where p.pCode=b.Product_pCode and p.pDeleteDate is null and b.bReviewDeleteDate is null and p.pName like ? "
							+ "group by p.pCode";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" + searchName + "%");
			
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
	
	public int store() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int count = 0;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select * from Product";
			
//			insert into Product (pPrice, pCategory, PExpirationDate, pName, pQuantity)
//			value ('2000', 'food', '2021-05-13', '콜라', '1');
			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				count++;
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
		
		return count;
		
	}
	
}
