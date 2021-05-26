package com.jspproject.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.jspproject.bbs.dto.BBuyDto;

public class BBuyDao { // 2021.05.16 조혜지 - 주문자 정보 불러오기 위해 DB에 있는 정보 불러오는 dao buyerInfo
					   // 2021.05.17 조혜지 - 장바구니에서 상품 주문 버튼 클릭 시 선택한 상품만 DB에서 불러오는 dao customerBuyList
   					   // 2021.05.18 조혜지 - 주문목록/배송조회 주문한 상품 리스트 불러오는 dao orderList
					   // 2021.05.19 조혜지 - 결제 완료 시 주문 번호 불러오는 dao bNumber, 결제 완료 시 주문한 상품 리스트 불러오는 dao bConfirmList, 결제 완료 시 배송 정보 불러오는 dao deliveryinfo
					   // 2021.05.20 조혜지 - 구매 취소 버튼 클릭 시 취소일자 업데이트하는 dao cancelDay, 선택 상품 주문 선택 후, 결제하기 버튼 클릭시 BnS와 Receiver DB에 insert하는 dao insert
					   // 2021.05.21 조혜지 - 전체 상품 주문 선택 후, 결제하기 버튼 클릭시 BnS와 Receiver DB에 insert하는 dao allInsert
					   // 2021.05.22 조혜지 - 장바구니 전체 구매를 하기 위해 장바구니 목록 DB에서 불러오는 dao allBuyList, 주문목록/배송조회 페이지 분할을 위해 몇 줄인지 세는 dao order
					   // 2021.05.23 조혜지 - 주문 취소한 상품 리스트 불러오는 dao cancelList, 주문취소목록 페이지 분할을 위해 몇 줄인지 세는 dao cancel
					   // 2021.05.24 조혜지 - 장바구니에서 구매한 제품의 pCode 구하는 dao pCodeList, 장바구니에서 구매한 제품 삭제하는 dao buyConfirmCartDelete
	
	// Field
	DataSource dataSource;
	
	// Constructor
	public BBuyDao() {
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
	// 2021.05.16 조혜지 - 주문자 정보 불러오기 위해 DB에 있는 정보 불러오는 method
	public BBuyDto buyerInfo(String strcId){
		BBuyDto dto = null;
		// 전역번수 쓰려고 여기에 만듬		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ResultSet resultSet = null;
		
		String queryA = "select cName, cTel, cEmail ";
		String queryB = "from Customer where cId = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(queryA + queryB);
			
			preparedStatement.setString(1, strcId);

			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
								
				String cName = resultSet.getString("cName");
				String cTel = resultSet.getString("cTel");
				String cEmail = resultSet.getString("cEmail");

				dto = new BBuyDto(cName, cTel, cEmail);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			// 마지막에 정리를 해줘야함 (웹이기 때문에!)
			try {
				// 제일 안쪽부터 정리 (rs -> ps -> connect)
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
	
	// 2021.05.20 조혜지 - 선택 상품 주문 선택 후, 결제하기 버튼 클릭시 BnS와 Receiver DB에 insert하는 method
	public void insert(String cId, String bNumber, String bRecName, String bRecPostalCode, String bRecAddress1, String bRecAddress2, String bRecTel, String bRecContent, HttpSession session) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String queryA = "insert into BnS (Customer_cId, bQuantity, Product_pCode, bNumber, bBuyDate) values (?,?,?,?,curdate())";
		ArrayList<BBuyDto> list = (ArrayList)session.getAttribute("BUY");

		for(int i=0; i<(int)session.getAttribute("size"); i++) {
			try {
				connection = dataSource.getConnection();
				
				preparedStatement = connection.prepareStatement(queryA);
				
				preparedStatement.setString(1, cId);
				preparedStatement.setInt(2, list.get(i).getwQuantity());
				preparedStatement.setInt(3, list.get(i).getpCode());
				preparedStatement.setString(4, bNumber);
				
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
		
		String queryB = "insert into Receiver (bRecName, bRecPostalCode, bRecAddress1, bRecAddress2, bRecTel, bRecContent, Customer_cId, bNumber) values (?,?,?,?,?,?,?,?)";
		try {
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(queryB);
			
			preparedStatement.setString(1, bRecName);
			preparedStatement.setString(2, bRecPostalCode);
			preparedStatement.setString(3, bRecAddress1);
			preparedStatement.setString(4, bRecAddress2);
			preparedStatement.setString(5, bRecTel);
			preparedStatement.setString(6, bRecContent);
			preparedStatement.setString(7, cId);
			preparedStatement.setString(8, bNumber);
			
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
		
		String queryC = "insert into Delivery (Customer_cId, Product_pCode, bNumber) values (?,?,?)";

		for(int i=0; i<(int)session.getAttribute("size"); i++) {
			try {
				connection = dataSource.getConnection();
				
				preparedStatement = connection.prepareStatement(queryC);
				
				preparedStatement.setString(1, cId);
				preparedStatement.setInt(2, list.get(i).getpCode());
				preparedStatement.setString(3, bNumber);
				
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
	
	// 2021.05.21 조혜지 - 전체 상품 주문 선택 후, 결제하기 버튼 클릭시 BnS와 Receiver DB에 insert하는 method
	public void allInsert(String cId, String bNumber, String bRecName, String bRecPostalCode, String bRecAddress1, String bRecAddress2, String bRecTel, String bRecContent, HttpSession session) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String queryA = "insert into BnS (Customer_cId, bQuantity, Product_pCode, bNumber, bBuyDate) values (?,?,?,?,curdate())";
		ArrayList<BBuyDto> list = (ArrayList)session.getAttribute("CARTBUY");
		
		for(int i=0; i<(int)session.getAttribute("asize"); i++) {
			try {
				connection = dataSource.getConnection();
				
				preparedStatement = connection.prepareStatement(queryA);
				
				preparedStatement.setString(1, cId);
				preparedStatement.setInt(2, list.get(i).getwQuantity());
				System.out.println(list.get(i).getwQuantity());
				preparedStatement.setInt(3, list.get(i).getpCode());
				System.out.println(list.get(i).getpCode());
				preparedStatement.setString(4, bNumber);
				
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
		
		String queryB = "insert into Receiver (bRecName, bRecPostalCode, bRecAddress1, bRecAddress2, bRecTel, bRecContent, Customer_cId, bNumber) values (?,?,?,?,?,?,?,?)";
		try {
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(queryB);
			
			preparedStatement.setString(1, bRecName);
			preparedStatement.setString(2, bRecPostalCode);
			preparedStatement.setString(3, bRecAddress1);
			preparedStatement.setString(4, bRecAddress2);
			preparedStatement.setString(5, bRecTel);
			preparedStatement.setString(6, bRecContent);
			preparedStatement.setString(7, cId);
			preparedStatement.setString(8, bNumber);
			
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
		
		String queryC = "insert into Delivery (Customer_cId, Product_pCode, bNumber) values (?,?,?)";

		for(int i=0; i<(int)session.getAttribute("asize"); i++) {
			try {
				connection = dataSource.getConnection();
				
				preparedStatement = connection.prepareStatement(queryC);
				
				preparedStatement.setString(1, cId);
				preparedStatement.setInt(2, list.get(i).getpCode());
				preparedStatement.setString(3, bNumber);
				
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
	
	// 2021.05.17 조혜지 - 장바구니에서 상품 주문 버튼 클릭 시 선택한 상품만 DB에서 불러오는 method
	public BBuyDto customerBuyList(String strcId, int inwId){
		BBuyDto dto = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String queryA = "select p.pName, w.wQuantity, p.pExpirationDate, p.pPriceDC, p.pCode from WishList as w, Product as p, Customer as c ";
			String queryB = "where w.Customer_cId = c.cId and w.Product_pCode = p.pCode and c.cId = ? and w.wId = ?";
			preparedStatement = connection.prepareStatement(queryA + queryB);
			
			preparedStatement.setString(1, strcId);
			preparedStatement.setInt(2, inwId);

			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String pName = resultSet.getString("pName");
				int wQuantity = resultSet.getInt("wQuantity");
				String pExpirationDate = resultSet.getString("pExpirationDate");
				int pPriceDC = resultSet.getInt("pPriceDC");
				int pCode = resultSet.getInt("pCode");
				
				dto = new BBuyDto(pName, wQuantity, pExpirationDate, pPriceDC, pCode);
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
	
	// 2021.05.18 조혜지 - 주문목록/배송조회 주문한 상품 리스트 불러오는 method
	public ArrayList<BBuyDto> orderList(String strcId, int from, int to){
		ArrayList<BBuyDto> dtos = new ArrayList<BBuyDto>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String queryA = "select datediff(b.bBuyDate, now()), b.bNumber, b.bBuyDate, p.pName, b.bQuantity, d.dCondition from BnS as b, Product as p, Customer as c, Delivery as d ";
			String queryB =	"where b.Customer_cId = c.cId and p.pCode = b.Product_pCode and d.Product_pCode = p.pCode and d.Customer_cId = c.cId and ";
			String queryC =	"d.Product_pCode = b.Product_pCode and d.Customer_cId = b.Customer_cId and d.bNumber = b.bNumber and c.cId = ? and b.bBuyCancelDate is null order by b.bSeq desc limit ?, ?";
			
			preparedStatement = connection.prepareStatement(queryA + queryB + queryC);
			
			preparedStatement.setString(1, strcId);
			preparedStatement.setInt(2, from);
			preparedStatement.setInt(3, to);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int datediff = resultSet.getInt(1);
				String bNumber = resultSet.getString("bNumber");
				String bBuyDate = resultSet.getString("bBuyDate");
				String pName = resultSet.getString("pName");
				int bQuantity = resultSet.getInt("bQuantity");
				String dCondition = resultSet.getString("dCondition");
				
				
				
				BBuyDto dto = new BBuyDto(datediff, bNumber, bBuyDate, pName, bQuantity, dCondition);
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
	
	// 2021.05.23 조혜지 - 주문 취소한 상품 리스트 불러오는 method
	public ArrayList<BBuyDto> cancelList(String strcId, int from, int to){
		ArrayList<BBuyDto> dtos = new ArrayList<BBuyDto>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String queryA = "select b.bNumber, b.bBuyDate, b.bBuyCancelDate, p.pName, b.bQuantity from BnS as b, Product as p, Customer as c ";
			String queryB =	"where b.Customer_cId = c.cId and p.pCode = b.Product_pCode ";
			String queryC =	"and c.cId = ? and b.bBuyCancelDate is not null order by b.bSeq desc limit ?, ?";
			
			preparedStatement = connection.prepareStatement(queryA + queryB + queryC);
			
			preparedStatement.setString(1, strcId);
			preparedStatement.setInt(2, from);
			preparedStatement.setInt(3, to);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String bNumber = resultSet.getString("bNumber");
				String bBuyDate = resultSet.getString("bBuyDate");
				String bBuyCancelDate = resultSet.getString("bBuyCancelDate");
				String pName = resultSet.getString("pName");
				int bQuantity = resultSet.getInt("bQuantity");
				
				BBuyDto dto = new BBuyDto(bNumber, bBuyDate, bBuyCancelDate, pName, bQuantity);
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
	
	// 2021.05.19 조혜지 - 결제 완료 시 주문 번호 불러오는 method
	public String bNumber(String strcId) {
		String bNumber = "";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select bNumber from BnS where bSeq = (select max(bSeq) from BnS) and Customer_cId = ?";
			
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, strcId);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				bNumber = resultSet.getString("bNumber");
	
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

		return bNumber;
	}
	
	// 2021.05.24 조혜지 - 장바구니에서 구매한 제품의 pCode 구하는 method
	public ArrayList<BBuyDto> pCodeList(String bNumber) {
		ArrayList<BBuyDto> dtos = new ArrayList<BBuyDto>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select p.pCode from BnS as b, Product as p where p.pCode = b.Product_pCode and b.bNumber = ?";
			
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, bNumber);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int pCode = resultSet.getInt("pCode");
				
				BBuyDto dto = new BBuyDto(pCode);
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
	
	// 2021.05.24 조혜지 - 장바구니에서 구매한 제품 삭제하는 method
	public void buyConfirmCartDelete(String cId, HttpSession session) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String queryA = "delete from WishList where Customer_cId = ? and Product_pCode = ?";
		ArrayList<BBuyDto> list = (ArrayList)session.getAttribute("pCode");
		
		for(int i=0; i<(int)session.getAttribute("psize"); i++) {
			try {
				connection = dataSource.getConnection();
				
				preparedStatement = connection.prepareStatement(queryA);
				
				preparedStatement.setString(1, cId);
				preparedStatement.setInt(2, list.get(i).getpCode());
				
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
	
	// 2021.05.19 조혜지 - 결제 완료 시 주문한 상품 리스트 불러오는 method
	public ArrayList<BBuyDto> buyConfirmList(String strcId, String strbNumber){
		ArrayList<BBuyDto> dtos = new ArrayList<BBuyDto>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String queryA = "select b.bNumber, p.pName, p.pExpirationDate, p.pPriceDC, b.bQuantity from BnS as b, Product as p, Customer as c ";
			String queryB = "where b.Customer_cId = c.cId and b.Product_pCode = p.pCode and c.cId = ? and b.bNumber = ?";
			
			preparedStatement = connection.prepareStatement(queryA + queryB);
			
			preparedStatement.setString(1, strcId);
			preparedStatement.setString(2, strbNumber);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String bNumber = resultSet.getString("bNumber");
				String pName = resultSet.getString("pName");
				String pExpirationDate = resultSet.getString("pExpirationDate");
				int pPriceDC = resultSet.getInt("pPriceDC");
				int bQuantity = resultSet.getInt("bQuantity");
				
				BBuyDto dto = new BBuyDto(bNumber, pName, pExpirationDate, pPriceDC, bQuantity);
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
	
	// 2021.05.19 조혜지 - 결제 완료 시 배송 정보 불러오는 method
	public BBuyDto deliveryinfo(String strcId, String strbNumber){
		BBuyDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ResultSet resultSet = null;
		
		String queryA = "select bRecName, bRecPostalCode, bRecAddress1, bRecAddress2, bRecTel, bRecContent ";
		String queryB = "from Receiver where Customer_cId = ? and bNumber = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(queryA + queryB);
			
			preparedStatement.setString(1, strcId);
			preparedStatement.setString(2, strbNumber);

			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
								
				String bRecName = resultSet.getString("bRecName");
				String bRecPostalCode = resultSet.getString("bRecPostalCode");
				String bRecAddress1 = resultSet.getString("bRecAddress1");
				String bRecAddress2 = resultSet.getString("bRecAddress2");
				String bRecTel = resultSet.getString("bRecTel");
				String bRecContent = resultSet.getString("bRecContent");
			
				dto = new BBuyDto(bRecName, bRecPostalCode, bRecAddress1, bRecAddress2, bRecTel, bRecContent);

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
	
	// 2021.05.20 조혜지 - 구매 취소 버튼 클릭 시 취소일자 업데이트하는 method
	public void cancelDay(String strbNumber) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			// select 문장에 ? 적어주기!
			String query = "update BnS set bBuyCancelDate = curdate() where bNumber = ? and bBuyDate >= date_add(now(), interval -1 day)";
			preparedStatement = connection.prepareStatement(query);
			
			// 지금은 문자로 써도 상관없으나 정상적으로 setInt로 적어야 함
			preparedStatement.setString(1, strbNumber);
			
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
	
	// 2021.05.22 조혜지 - 장바구니 전체 구매를 하기 위해 장바구니 목록 DB에서 불러오는 method
	public ArrayList<BBuyDto> allBuyList(String strcId){
		ArrayList<BBuyDto> dtos = new ArrayList<BBuyDto>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String queryA = "select p.pName, w.wQuantity, p.pExpirationDate, p.pPriceDC, w.wId, p.pCode from WishList as w, Product as p, Customer as c ";
			String queryB = "where w.Customer_cId = c.cId and w.Product_pCode = p.pCode and c.cId = ?";
			preparedStatement = connection.prepareStatement(queryA + queryB);
			
			preparedStatement.setString(1, strcId);

			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String pName = resultSet.getString("pName");
				int wQuantity = resultSet.getInt("wQuantity");
				String pExpirationDate = resultSet.getString("pExpirationDate");
				int pPriceDC = resultSet.getInt("pPriceDC");
				int wId = resultSet.getInt("wId");
				int pCode = resultSet.getInt("pCode");
				
				BBuyDto dto = new BBuyDto(pName, wQuantity, pExpirationDate, pPriceDC, wId, pCode);
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
	
	// 2021.05.22 조혜지 - 주문목록/배송조회 페이지 분할을 위해 몇 줄인지 세는 method
	public int order(String strcId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int orderCount = 0;
		
		try {
			connection = dataSource.getConnection();
			
			String queryA = "select datediff(b.bBuyDate, now()), b.bNumber, b.bBuyDate, p.pName, b.bQuantity, d.dCondition from BnS as b, Product as p, Customer as c, Delivery as d ";
			String queryB =	"where b.Customer_cId = c.cId and p.pCode = b.Product_pCode and d.Product_pCode = p.pCode and d.Customer_cId = c.cId and ";
			String queryC =	"d.Product_pCode = b.Product_pCode and d.Customer_cId = b.Customer_cId and d.bNumber = b.bNumber and c.cId = ? and b.bBuyCancelDate is null order by b.bSeq desc ";			
			
			preparedStatement = connection.prepareStatement(queryA + queryB + queryC);
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
	
	// 2021.05.23 조혜지 - 주문취소목록 페이지 분할을 위해 몇 줄인지 세는 method
	public int cancel(String strcId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int cancelCount = 0;
		
		try {
			connection = dataSource.getConnection();
			
			String queryA = "select b.bNumber, b.bBuyDate, b.bBuyCancelDate, p.pName, b.bQuantity from BnS as b, Product as p, Customer as c ";
			String queryB =	"where b.Customer_cId = c.cId and p.pCode = b.Product_pCode ";
			String queryC =	"and c.cId = ? and b.bBuyCancelDate is not null order by b.bSeq desc";		
			
			preparedStatement = connection.prepareStatement(queryA + queryB + queryC);
			preparedStatement.setString(1, strcId);
			
			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				cancelCount++;
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
		
		return cancelCount;
	}
}
