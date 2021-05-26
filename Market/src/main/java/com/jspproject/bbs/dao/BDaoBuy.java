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

import com.jspproject.bbs.dto.BDtoBuy;

public class BDaoBuy {

	DataSource dataSource;

	public BDaoBuy() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Market");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<BDtoBuy> list(int start, int end) {
		ArrayList<BDtoBuy> dtos = new ArrayList<BDtoBuy>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String buyCancel;

		try {
			connection = dataSource.getConnection();

			String query = "select bSeq, B.bNumber, pCategory, pName, bBuyDate, bBuyCancelDate from BnS as B "
					+ "join Product as P on B.Product_pCode = P.pCode "
					+ "where bBuyDate is not null limit ?, ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				String bNumber = resultset.getString("bNumber");
				String pCategory = resultset.getString("pCategory");
				String pName = resultset.getString("pName");
				Timestamp BuyDate = resultset.getTimestamp("bBuyDate");
				Timestamp bBuyCancelDate = resultset.getTimestamp("bBuyCancelDate");
				String bBuyDate = new SimpleDateFormat("yyyy-MM-dd").format(BuyDate);

				if (bBuyCancelDate != null) {
					buyCancel = "취소";
				} else {
					buyCancel = "주문완료";
				}

				BDtoBuy dto = new BDtoBuy(bNumber, pCategory, pName, buyCancel, bBuyDate);
				dtos.add(dto);

//				BDto dto = new BDto(pCode, pCategory, pName, pPrice, pExpirationDate, pQuantity, pStatus);
//				dtos.add(dto);
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

	public int buy() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int buyCount = 0;

		try {
			connection = dataSource.getConnection();

			String query = "select * from BnS";

//			insert into Product (pPrice, pCategory, PExpirationDate, pName, pQuantity)
//			value ('2000', 'food', '2021-05-13', '콜라', '1');
			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				buyCount++;
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

		return buyCount;

	}

	public int buyTrue() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int buyTure = 0;

		try {
			connection = dataSource.getConnection();

			String query = "SELECT * FROM Market.BnS where bBuyCancelDate is null";

//			insert into Product (pPrice, pCategory, PExpirationDate, pName, pQuantity)
//			value ('2000', 'food', '2021-05-13', '콜라', '1');
			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				buyTure++;
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

		return buyTure;

	}

	public int buyCancel() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int buyCancel = 0;

		try {
			connection = dataSource.getConnection();

			String query = "SELECT * FROM Market.BnS where bBuyCancelDate is not null";

//			insert into Product (pPrice, pCategory, PExpirationDate, pName, pQuantity)
//			value ('2000', 'food', '2021-05-13', '콜라', '1');
			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				buyCancel++;
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

		return buyCancel;

	}

	public ArrayList<BDtoBuy> buyTrueList(int start, int end) {
		ArrayList<BDtoBuy> dtos = new ArrayList<BDtoBuy>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String buyCancel;

		try {
			connection = dataSource.getConnection();

			String query = "select B.bNumber, P.pCategory, P.pName, B.bBuyDate, B.bBuyCancelDate \n"
					+ "from BnS as B left join Product as P on B.Product_pCode = P.pCode \n"
					+ "where B.bBuyCancelDate is null limit ?, ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				String bNumber = resultset.getString("bNumber");
				String pCategory = resultset.getString("pCategory");
				String pName = resultset.getString("pName");
				Timestamp BuyDate = resultset.getTimestamp("bBuyDate");
				Timestamp bBuyCancelDate = resultset.getTimestamp("bBuyCancelDate");
				String bBuyDate = new SimpleDateFormat("yyyy-MM-dd").format(BuyDate);

				if (bBuyCancelDate != null) {
					buyCancel = "취소";
				} else {
					buyCancel = "주문완료";
				}

				BDtoBuy dto = new BDtoBuy(bNumber, pCategory, pName, buyCancel, bBuyDate);
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

	public ArrayList<BDtoBuy> buyCancelList(int start, int end) {
		ArrayList<BDtoBuy> dtos = new ArrayList<BDtoBuy>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String buyCancel;

		try {
			connection = dataSource.getConnection();

			String query = "select B.bNumber, P.pCategory, P.pName, B.bBuyDate, B.bBuyCancelDate "
					+ "from BnS as B left join Product as P on B.Product_pCode = P.pCode "
					+ "where B.bBuyCancelDate is not null limit ?, ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				String bNumber = resultset.getString("bNumber");
				String pCategory = resultset.getString("pCategory");
				String pName = resultset.getString("pName");
				Timestamp BuyDate = resultset.getTimestamp("bBuyDate");
				Timestamp bBuyCancelDate = resultset.getTimestamp("bBuyCancelDate");
				String bBuyDate = new SimpleDateFormat("yyyy-MM-dd").format(BuyDate);
				
				if (bBuyCancelDate != null) {
					buyCancel = "취소";
				} else {
					buyCancel = "주문완료";
				}

				BDtoBuy dto = new BDtoBuy(bNumber, pCategory, pName, buyCancel, bBuyDate);
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

	public ArrayList<BDtoBuy> search(String search, String searchtxt, int start, int end) {
		String searchCheck = null;
		String searchtxtCheck = null;
		System.out.println("ㅇ다오인데 나 아무것도 안적었는데 왜 있다고해?" + searchtxt);

		if (searchtxt.length() == 0) {
			searchCheck = "";
			searchtxtCheck = "";
		} else {
			searchCheck = search;
			searchtxtCheck = " like '%" + searchtxt + "%'";

		}

		ArrayList<BDtoBuy> dtos = new ArrayList<BDtoBuy>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String buyCancel;

		try {
			connection = dataSource.getConnection();
			String whereStatement = "select B.bNumber, P.pCategory, P.pName, B.bBuyDate, B.bBuyCancelDate "
					+ "from BnS as B left join Product as P on B.Product_pCode = P.pCode "
					+ "where B.bBuyDate is not null" + searchCheck + searchtxtCheck + " limit ?, ?";
			System.out.println("도대체 어디서 출력되는거야 ?" + whereStatement);

			preparedStatement = connection.prepareStatement(whereStatement);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				String bNumber = resultset.getString("bNumber");
				String pCategory = resultset.getString("pCategory");
				String pName = resultset.getString("pName");
				Timestamp BuyDate = resultset.getTimestamp("bBuyDate");
				Timestamp bBuyCancelDate = resultset.getTimestamp("bBuyCancelDate");
				String bBuyDate = new SimpleDateFormat("yyyy-MM-dd").format(BuyDate);

				if (bBuyCancelDate != null) {
					buyCancel = "취소";
				} else {
					buyCancel = "주문완료";
				}

				BDtoBuy dto = new BDtoBuy(bNumber, pCategory, pName, buyCancel, bBuyDate);
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

	public int searchBuyCount(String search, String searchtxt) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int searchBuyCount = 0;
		String searchCheck = null;
		String searchtxtCheck = null;

		if (searchtxt.length() == 0) {
			searchCheck = "";
			searchtxtCheck = "";
		} else {
			searchCheck = search;
			searchtxtCheck = " like '%" + searchtxt + "%'";

		}

		try {
			connection = dataSource.getConnection();

			String whereStatement = "select B.bNumber, P.pCategory, P.pName, B.bBuyDate, B.bBuyCancelDate "
					+ "from BnS as B left join Product as P on B.Product_pCode = P.pCode "
					+ "where B.bBuyDate is not null" + searchCheck + searchtxtCheck;

			preparedStatement = connection.prepareStatement(whereStatement);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				searchBuyCount++;
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

		return searchBuyCount;

	}

	public BDtoBuy selectBuy(String selecCode) {
		BDtoBuy dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String buyCancel;

		try {
			connection = dataSource.getConnection();

			String query = "select bNumber, pCategory, pName, bReviewContent, bReviewScore, bBuyDate, bReviewWriteDate, bReviewDeleteDate, bBuyCancelDate "
					+ "from BnS join Product on BnS.Product_pCode = Product.pCode where bNumber = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, selecCode);

			resultset = preparedStatement.executeQuery();
//			select pCode, pCategory, pName, pPrice, pExpirationDate, pQuantity, pStatus from Product
			if (resultset.next()) {
				String bNumber = resultset.getString("bNumber");
				String pCategory = resultset.getString("pCategory");
				String pName = resultset.getString("pName");
				String bReviewContent = resultset.getString("bReviewContent");
				int bReviewScore = resultset.getInt("bReviewScore");
				Timestamp BuyDate = resultset.getTimestamp("bBuyDate");
				Timestamp bReviewWriteDate = resultset.getTimestamp("bReviewWriteDate");
				Timestamp bReviewDeleteDate = resultset.getTimestamp("bReviewDeleteDate");
				Timestamp bBuyCancelDate = resultset.getTimestamp("bBuyCancelDate");

				String bBuyDate = new SimpleDateFormat("yyyy-MM-dd").format(BuyDate);
				
				
				if (bBuyCancelDate != null) {
					buyCancel = "취소";
				} else {
					buyCancel = "주문완료";
				}

				dto = new BDtoBuy(bNumber, pCategory, pName, bReviewContent, bReviewScore, bBuyDate, bBuyCancelDate,
						bReviewWriteDate, bReviewDeleteDate, buyCancel);

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
		return dto;
	}

}
