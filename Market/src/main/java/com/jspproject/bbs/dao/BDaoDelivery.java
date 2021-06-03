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
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Market");

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
		
		String query = "SET SESSION sql_mode = 'NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES'";
		System.out.println(query);
		
		try {
			connection = dataSource.getConnection();

			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

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
		
		

		String queryList = "select D.bNumber, pCategory, pName, dCondition from Delivery as D\n"
				+ "join Product as P on P.pCode = D.Product_pCode\n"
				+ "join BnS as B on D.bNumber = B.bNumber where bBuyCancelDate is null\n"
				+ "group by B.bNumber limit ?, ?";
		try {
			connection = dataSource.getConnection();

			preparedStatement = connection.prepareStatement(queryList);
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
		
		String query = "SET SESSION sql_mode = 'NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES'";
		System.out.println(query);
		
		try {
			connection = dataSource.getConnection();

			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

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
		
		String queryCount = "select D.bNumber, pCategory, pName, dCondition from Delivery as D\n"
				+ "join Product as P on P.pCode = D.Product_pCode\n"
				+ "join BnS as B on D.bNumber = B.bNumber where bBuyCancelDate is null\n"
				+ "group by B.bNumber";
		
		try {
			connection = dataSource.getConnection();
			
			
			
			preparedStatement = connection.prepareStatement(queryCount);
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
		
		String query = "SET SESSION sql_mode = 'NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES'";
		System.out.println(query);
		
		try {
			connection = dataSource.getConnection();

			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

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
		
		
		String queryCount = "select D.bNumber, pCategory, pName, dCondition from Delivery as D\n"
				+ "join Product as P on P.pCode = D.Product_pCode\n"
				+ "join BnS as B on D.bNumber = B.bNumber where bBuyCancelDate is null and dCondition = '배송완료'\n"
				+ "group by B.bNumber";
		try {
			connection = dataSource.getConnection();
			
			
			preparedStatement = connection.prepareStatement(queryCount);
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
		
		String query = "SET SESSION sql_mode = 'NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES'";
		System.out.println(query);
		
		try {
			connection = dataSource.getConnection();

			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

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
		
		
		String queryCount = "select D.bNumber, pCategory, pName, dCondition from Delivery as D\n"
				+ "join Product as P on P.pCode = D.Product_pCode\n"
				+ "join BnS as B on D.bNumber = B.bNumber where bBuyCancelDate is null and dCondition = '배송중'\n"
				+ "group by B.bNumber";
		
		try {
			connection = dataSource.getConnection();
			
			
			preparedStatement = connection.prepareStatement(queryCount);
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
	
	// 검색 리스트  /// #####@%@#&$%@@#$---------------------------------------------------------------------- jsp도없음
	public ArrayList<BDtoDelivery> search(String search, String searchtxt, int start, int end) {
		
		ArrayList<BDtoDelivery> dtos = new ArrayList<BDtoDelivery>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
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
			
			String query = "SET SESSION sql_mode = 'NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES'";
			System.out.println(query);
			
			try {
				connection = dataSource.getConnection();

				preparedStatement = connection.prepareStatement(query);
				resultset = preparedStatement.executeQuery();

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
		
		
		String whereStatement = "select D.bNumber, pCategory, pName, dCondition from Delivery as D\n"
				+ "join Product as P on P.pCode = D.Product_pCode" + searchCheck + searchtxtCheck + " group by D.bNumber limit ?, ?";
		
		System.out.println(whereStatement);
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
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
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
			
			String query = "SET SESSION sql_mode = 'NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES'";
			System.out.println(query);
			
			try {
				connection = dataSource.getConnection();

				preparedStatement = connection.prepareStatement(query);
				resultset = preparedStatement.executeQuery();

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
			
		
		String whereStatement = "select D.bNumber, pCategory, pName, dCondition from Delivery as D \n"
				+ "join Product as P on P.pCode = D.Product_pCode" + searchCheck + searchtxtCheck;
		
		System.out.println(whereStatement);
		
		
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
		
		String query = "SET SESSION sql_mode = 'NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES'";
		System.out.println(query);
		
		try {
			connection = dataSource.getConnection();

			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

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
		
		
		String queryList = "select D.bNumber, pCategory, pName, dCondition from Delivery as D "
				+ "join Product as P on P.pCode = D.Product_pCode where D.dCondition = '배송완료' group by D.bNumber limit ?, ?";
		try {
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(queryList);
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
		
		String query = "SET SESSION sql_mode = 'NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES'";
		System.out.println(query);
		
		try {
			connection = dataSource.getConnection();

			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

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
		
		String queryList = "select D.bNumber, pCategory, pName, dCondition from Delivery as D \n"
				+ "join Product as P on P.pCode = D.Product_pCode where D.dCondition = '배송중' group by D.bNumber limit ?, ?";
		
		try {
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(queryList);
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
		
		String query = "SET SESSION sql_mode = 'NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES'";
		System.out.println(query);
		
		try {
			connection = dataSource.getConnection();

			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

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
		
		
		String querySelect = "select R.bNumber, bBuyDate, bRecName, bRecTel, bRecPostalCode, bRecAddress1, bRecAddress2, dStartDate, dEndDate from Receiver as R \n"
				+ "join BnS as B on R.Customer_cId = B.Customer_cId \n"
				+ "join Delivery as D on R.bNumber = D.bNumber and B.Product_pCode = D.Product_pCode where R.bNumber = ?"
				+ "group by R.bNumber";
		try {
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(querySelect);
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

			String query = "update Delivery set dCondition = '배송중', dStartDate = now() \n"
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

			String query = "update Delivery set dCondition = '배송완료', dEndDate = now() "
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
