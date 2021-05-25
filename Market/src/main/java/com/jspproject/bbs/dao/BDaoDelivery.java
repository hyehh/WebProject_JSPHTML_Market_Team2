package com.jspproject.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jspproject.bbs.dto.BDtoDelivery;

public class BDaoDelivery {
	
	DataSource dataSource;

	public BDaoDelivery() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/market");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 배송리스트 전체출력
	public ArrayList<BDtoDelivery> list(int start, int end) {
		ArrayList<BDtoDelivery> dtos = new ArrayList<BDtoDelivery>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;

		try {
			connection = dataSource.getConnection();

			String query = "select bNumber, pCategory, pName, dCondition "
					+ "from Receiver as R join Delivery as D "
					+ "on R.Customer_cId = D.Customer_cId "
					+ "join Product as P on D.Product_pCode = P.pCode limit ?, ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				String bNumber = resultset.getString("bNumber");
				String pCategory = resultset.getString("pCategory");
				String pName = resultset.getString("pName");
				String dCondition = resultset.getString("dCondition");

				
				BDtoDelivery dto = new BDtoDelivery(bNumber, pCategory, pName, dCondition);
				dtos.add(dto);

			}
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
		return dtos;

	}

	// 전체 갯수
	public int deliveryAll() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int deliveryAll = 0;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select bNumber, pCategory, pName, dCondition "
					+ "from Receiver as R join Delivery as D "
					+ "on R.Customer_cId = D.Customer_cId "
					+ "join Product as P on D.Product_pCode = P.pCode";
			
			
			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				deliveryAll++;
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
		
		return deliveryAll;
		
	}
	
	// 배송완료 갯수
	public int deliveryEnd() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int deliveryEnd = 0;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select bNumber, pCategory, pName, dCondition "
					+ "from Receiver as R join Delivery as D "
					+ "on R.Customer_cId = D.Customer_cId "
					+ "join Product as P on D.Product_pCode = P.pCode where D.dEndDate is not null";
			
			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				deliveryEnd++;
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
		
		return deliveryEnd;
		
	}
	
	// 배송중 갯수
	public int deliveryING() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int deliveryING = 0;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select bNumber, pCategory, pName, dCondition "
					+ "from Receiver as R join Delivery as D "
					+ "on R.Customer_cId = D.Customer_cId "
					+ "join Product as P on D.Product_pCode = P.pCode where D.dEndDate is null";
			
			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				deliveryING++;
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
		
		return deliveryING;
		
	}
	
	// 검색 리스트
	public ArrayList<BDtoDelivery> search(String search, String searchtxt, int start, int end) {
		String searchCheck = null;
		String searchtxtCheck = null;
		System.out.println(search);
		
			if (search.equals("allshow")){
				searchCheck = "";
				searchtxtCheck = "";
			} else {
				searchCheck = search;
				searchtxtCheck = " like '%" + searchtxt + "%'";
				
			}
		
		String whereStatement = "select bNumber, pCategory, pName, dCondition "
				+ "from Receiver as R join Delivery as D "
				+ "on R.Customer_cId = D.Customer_cId "
				+ "join Product as P on D.Product_pCode = P.pCode" + searchCheck + searchtxtCheck + " limit ?, ?";
		
		System.out.println(whereStatement);
		
		ArrayList<BDtoDelivery> dtos = new ArrayList<BDtoDelivery>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		
		try {
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(whereStatement);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				String bNumber = resultset.getString("bNumber");
				String pCategory = resultset.getString("pCategory");
				String pName = resultset.getString("pName");
				String dCondition = resultset.getString("dCondition");

				
				BDtoDelivery dto = new BDtoDelivery(bNumber, pCategory, pName, dCondition);
				dtos.add(dto);

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
		return dtos;
	}
	
	// 검색 갯수
	public int searchDeliveryCount(String search, String searchtxt, int start, int end) {
		String searchCheck = null;
		String searchtxtCheck = null;
		int searchDeliveryCount = 0;
		
			if (search.equals("allshow")){
				searchCheck = "";
				searchtxtCheck = "";
			} else {
				searchCheck = search;
				searchtxtCheck = " like '%" + searchtxt + "%'";
				
			}
		
		String whereStatement = "select bNumber, pCategory, pName, dCondition "
				+ "from Receiver as R join Delivery as D "
				+ "on R.Customer_cId = D.Customer_cId "
				+ "join Product as P on D.Product_pCode = P.pCode" + searchCheck + searchtxtCheck + " limit ?, ?";
		
		System.out.println(whereStatement);
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		
		try {
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(whereStatement);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				searchDeliveryCount++;
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
		
		return searchDeliveryCount;
	}
	
	// 배송완료 리스트
	public ArrayList<BDtoDelivery> deliveryEndList(int start, int end){
		ArrayList<BDtoDelivery> dtos = new ArrayList<BDtoDelivery>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select bNumber, pCategory, pName, dCondition "
					+ "from Receiver as R join Delivery as D "
					+ "on R.Customer_cId = D.Customer_cId "
					+ "join Product as P on D.Product_pCode = P.pCode where D.dCondition = '배송완료' limit ?, ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				String bNumber = resultset.getString("bNumber");
				String pCategory = resultset.getString("pCategory");
				String pName = resultset.getString("pName");
				String dCondition = resultset.getString("dCondition");

				
				BDtoDelivery dto = new BDtoDelivery(bNumber, pCategory, pName, dCondition);
				dtos.add(dto);

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
		return dtos;
		
	}
	
	// 배송중 리스트
	public ArrayList<BDtoDelivery> deliveryINGList(int start, int end){
		ArrayList<BDtoDelivery> dtos = new ArrayList<BDtoDelivery>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select bNumber, pCategory, pName, dCondition "
					+ "from Receiver as R join Delivery as D "
					+ "on R.Customer_cId = D.Customer_cId "
					+ "join Product as P on D.Product_pCode = P.pCode where D.dCondition = '배송중' limit ?, ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				String bNumber = resultset.getString("bNumber");
				String pCategory = resultset.getString("pCategory");
				String pName = resultset.getString("pName");
				String dCondition = resultset.getString("dCondition");

				
				BDtoDelivery dto = new BDtoDelivery(bNumber, pCategory, pName, dCondition);
				dtos.add(dto);

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
		return dtos;
		
	}
	
	// 배송 상세보기
	public BDtoDelivery selectOrderPage(String selecCode) {
		BDtoDelivery dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String bBuyDate = null;
		String dStartDate = null;
		String dEndDate = null;
		
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select R.bNumber, bBuyDate, bRecName, bRecTel, bRecPostalCode, bRecAddress1, bRecAddress2, dStartDate, dEndDate from Receiver as R "
					+ "join BnS as B on R.Customer_cId = B.Customer_cId "
					+ "join Delivery as D on D.Customer_cId = R.Customer_cId where R.bNumber = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, selecCode);
			
			resultset = preparedStatement.executeQuery();
			if (resultset.next()) {
				String bNumber = resultset.getString("bNumber");
				Timestamp BuyDate = resultset.getTimestamp("bBuyDate");
				String bRecName = resultset.getString("bRecName");
				String bRecTel = resultset.getString("bRecTel");
				String bRecPostalCode = resultset.getString("bRecPostalCode");
				String bRecAddress1 = resultset.getString("bRecAddress1");
				String bRecAddress2 = resultset.getString("bRecAddress2");
				Timestamp StartDate = resultset.getTimestamp("dStartDate");
				Timestamp EndDate = resultset.getTimestamp("dEndDate");
				
				if (BuyDate == null) {
					bBuyDate = "";
				} else {
					bBuyDate = new SimpleDateFormat("yyyy-MM-dd").format(BuyDate);
				}
				
				if (StartDate == null) {
					dStartDate = "";
				} else {
					dStartDate = new SimpleDateFormat("yyyy-MM-dd").format(StartDate);
				}
				
				if (EndDate == null) {
					dEndDate = "";
				} else {
					dEndDate = new SimpleDateFormat("yyyy-MM-dd").format(EndDate);
				}
				
				
				
				dto = new BDtoDelivery(bNumber, dStartDate, dEndDate, bBuyDate, bRecName, bRecTel, bRecPostalCode, bRecAddress1, bRecAddress2);
				
				
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
		return dto;
	}
	
	// 배곳ㅇ 상세보기
	public ArrayList<BDtoDelivery> selectDPageList(String getName) {
		ArrayList<BDtoDelivery> dtos = new ArrayList<BDtoDelivery>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;

		try {
			connection = dataSource.getConnection();

			String query = "select pName, pCategory, bQuantity from Product as P "
					+ "join BnS as B on B.Product_pCode = P.pCode "
					+ "where bNumber = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, getName);
			
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				String pName = resultset.getString("pName");
				String pCategory = resultset.getString("pCategory");
				int bQuantity = resultset.getInt("bQuantity");
				
				BDtoDelivery dto = new BDtoDelivery(pCategory, pName, bQuantity);
				dtos.add(dto);

			}
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
		return dtos;

	}
	
	// 배송상세보기 위한 구매자이름구하기
	public String setCName(String selecCode) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String cName = null;

		try {
			connection = dataSource.getConnection();

			String query = "select C.cName from Buy as B "
					+ "inner join Customer as C on B.cId = C.cId "
					+ "inner join QnAnD as D on D.pCode = B.pCode where B.bNumber = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, selecCode);
			
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				cName = resultset.getString("cName");
			}
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
		return cName;

	}
	
	
	public void deliveryStart(String bNumber) {
		System.out.println("다오???");
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();

			String query = "update Delivery as D join BnS as B on D.Customer_cId = B.Customer_cId "
					+ "set dCondition = '배송중', dStartDate = now() "
					+ "where bNumber = ?";
			System.out.println(query);
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, bNumber);

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
	
	public void deliveryFinish(String bNumber) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();

			String query = "update Delivery as D join BnS as B on D.Customer_cId = B.Customer_cId "
					+ "set dCondition = '배송완료', dEndDate = now() "
					+ "where bNumber = ?";
			System.out.println(query);
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, bNumber);

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