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

import com.jspproject.bbs.dto.BDtoQnA;

public class BDaoQnA {

	DataSource dataSource;

	public BDaoQnA() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Market");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<BDtoQnA> list(int start, int end) {
		ArrayList<BDtoQnA> dtos = new ArrayList<BDtoQnA>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;

		String qAnswerStatus = null;

		try {
			connection = dataSource.getConnection();

			String query = "select QnACode, bNumber, qTitle, qAddDate, qAnswerStatus from BnS as B "
					+ "join QnA as Q on B.Customer_cId = Q.Customer_cId order by QnACode desc limit ?, ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				int qnACode = resultset.getInt("QnACode");
				String bNumber = resultset.getString("bNumber");
				String qTitle = resultset.getString("qTitle");
				Timestamp AddDate = resultset.getTimestamp("qAddDate");
				String AnswerStatus = resultset.getString("qAnswerStatus");
				String qAddDate = new SimpleDateFormat("yyy-MM-dd").format(AddDate);

				if (AnswerStatus == null) {
					qAnswerStatus = "미답변";
				} else {
					qAnswerStatus = AnswerStatus;
				}

				BDtoQnA dto = new BDtoQnA(qnACode, qAddDate, bNumber, qTitle, qAnswerStatus);
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

	public int allQnA() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int allQnACount = 0;

		try {
			connection = dataSource.getConnection();

			String query = "select bNumber, qTitle, qAddDate, qAnswerStatus "
					+ "from BnS as B join QnA as Q on B.Customer_cId = Q.Customer_cId";

//			insert into Product (pPrice, pCategory, PExpirationDate, pName, pQuantity)
//			value ('2000', 'food', '2021-05-13', '콜라', '1');
			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				allQnACount++;
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

		return allQnACount;

	}

	public int QnATure() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int QnATure = 0;

		try {
			connection = dataSource.getConnection();

			String query = "SELECT * FROM Market.QnA where qAnswerStatus is not null";

			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				QnATure++;
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

		return QnATure;

	}

	public int QnAFalse() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int QnAFalse = 0;

		try {
			connection = dataSource.getConnection();

			String query = "SELECT * FROM Market.QnA where qAnswerStatus is null";

//			insert into Product (pPrice, pCategory, PExpirationDate, pName, pQuantity)
//			value ('2000', 'food', '2021-05-13', '콜라', '1');
			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				QnAFalse++;
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

		return QnAFalse;

	}

	public ArrayList<BDtoQnA> QnATureList(int start, int end) {
		ArrayList<BDtoQnA> dtos = new ArrayList<BDtoQnA>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;

		try {
			connection = dataSource.getConnection();

			String query = "select QnACode, bNumber, qTitle, qAddDate, qAnswerStatus "
					+ "from BnS as B join QnA as Q on B.Customer_cId = Q.Customer_cId "
					+ "where qAnswerStatus is not null order by QnACode desc limit ?, ?";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();
			String qAnswerStatus = null;

			while (resultset.next()) {
				int qnACode = resultset.getInt("QnACode");
				String bNumber = resultset.getString("bNumber");
				String qTitle = resultset.getString("qTitle");
				Timestamp AddDate = resultset.getTimestamp("qAddDate");
				String AnswerStatus = resultset.getString("qAnswerStatus");
				String qAddDate = new SimpleDateFormat("yyy-MM-dd").format(AddDate);

				if (AnswerStatus == null) {
					qAnswerStatus = "미답변";
				} else {
					qAnswerStatus = AnswerStatus;
				}

				BDtoQnA dto = new BDtoQnA(qnACode, qAddDate, bNumber, qTitle, qAnswerStatus);
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

	public ArrayList<BDtoQnA> QnAFalseList(int start, int end) {
		ArrayList<BDtoQnA> dtos = new ArrayList<BDtoQnA>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;

		try {
			connection = dataSource.getConnection();
			String query = "select QnACode, bNumber, qTitle, qAddDate, qAnswerStatus "
					+ "from BnS as B join QnA as Q on B.Customer_cId = Q.Customer_cId "
					+ "where qAnswerStatus is null order by QnACode desc limit ?, ?";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();
			String qAnswerStatus = null;

			while (resultset.next()) {
				int qnACode = resultset.getInt("QnACode");
				String bNumber = resultset.getString("bNumber");
				String qTitle = resultset.getString("qTitle");
				Timestamp AddDate = resultset.getTimestamp("qAddDate");
				String AnswerStatus = resultset.getString("qAnswerStatus");
				String qAddDate = new SimpleDateFormat("yyy-MM-dd").format(AddDate);

				if (AnswerStatus == null) {
					qAnswerStatus = "미답변";
				} else {
					qAnswerStatus = AnswerStatus;
				}

				BDtoQnA dto = new BDtoQnA(qnACode, qAddDate, bNumber, qTitle, qAnswerStatus);
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

	public BDtoQnA selectQnA(String selectCode) {
		BDtoQnA dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String aContent = null;
		String qAddDate = null;
		String aAddDate = null;

		try {
			connection = dataSource.getConnection();

			String query = "select QnACode, bNumber, qTitle, cName, qAddDate, qContent, aDeleteDate, qAnswerStatus, "
					+ "aContent, aAddDate from QnA as Q "
					+ "join BnS as B on Q.Customer_cId = B.Customer_cId and Q.Product_pCode = B.Product_pCode "
					+ "join Customer as C on Q.Customer_cId = C.cId where B.bNumber = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, selectCode);

			resultset = preparedStatement.executeQuery();
//			select pCode, pCategory, pName, pPrice, pExpirationDate, pQuantity, pStatus from Product
			if (resultset.next()) {
				int qnACode = resultset.getInt("QnACode");
				String bNumber = resultset.getString("bNumber");
				String qTitle = resultset.getString("qTitle");
				String cName = resultset.getString("cName");
				Timestamp AddDate = resultset.getTimestamp("qAddDate");
				String qContent = resultset.getString("qContent");
				Timestamp aDeleteDate = resultset.getTimestamp("aDeleteDate");
				String qAnswerStatus = resultset.getString("qAnswerStatus");
				String Content = resultset.getString("aContent");
				Timestamp a_AddDate = resultset.getTimestamp("aAddDate");
				
				if (AddDate == null) {
					qAddDate = "";
				} else {
					qAddDate = new SimpleDateFormat("yyy-MM-dd").format(AddDate);
				}
				
				if (a_AddDate == null) {
					aAddDate = "";
				} else {
					aAddDate = new SimpleDateFormat("yyyy-MM-dd").format(a_AddDate);
				}
				
				
				
				

				if (aDeleteDate == null) {
					aContent = Content;
				} else {
					aContent = "";
				}

				dto = new BDtoQnA(qnACode, qContent, qAddDate, bNumber, qTitle, qAnswerStatus, cName, aContent, aDeleteDate,
						aAddDate);

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

	public void QnAEdit(String qnACode, String aContent) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();

			String query = "update QnA as Q join BnS as B on Q.Customer_cId = B.Customer_cId\n"
					+ "set aContent = ?, aEditeDate = now() where QnACode = ?";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, aContent);
			preparedStatement.setString(2, qnACode);

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

	public void insertQnA(String aContent, String qnACode) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();

			String query = "update QnA as Q join BnS as B on Q.Customer_cId = B.Customer_cId "
					+ "set qAnswerStatus = '답변완료', aAddDate = now(), aContent = ? where QnACode = ?";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, aContent);
			preparedStatement.setString(2, qnACode);

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
	
	
	public void deleteQnA(String qnACode){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "update QnA as Q join BnS as B on Q.Customer_cId = B.Customer_cId "
					+ "set qAnswerStatus = null, aDeleteDate = now(), aContent = null where qnACode = ?";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, qnACode);
			
			preparedStatement.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) preparedStatement.close();
				if (connection != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<BDtoQnA> searchQnA(String searchtxt, int start, int end) {
		String searchtxtCheck = null;
		ArrayList<BDtoQnA> dtos = new ArrayList<BDtoQnA>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String qAnswerStatus;
		
			if (searchtxt == null){
				searchtxtCheck = "";
			} else {
				searchtxtCheck = "where bNumber like '%" + searchtxt + "%' ";
				
			}
		
		String whereStatement = "select QnACode, bNumber, qTitle, qAddDate, qAnswerStatus "
				+ "from BnS as B join QnA as Q on B.Customer_cId = Q.Customer_cId "
				+ searchtxtCheck + "order by QnACode desc limit ?, ?";
		
		System.out.println(whereStatement);
		
		
		try {
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(whereStatement);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				int qnACode = resultset.getInt("QnACode");
				String bNumber = resultset.getString("bNumber");
				String qTitle = resultset.getString("qTitle");
				Timestamp AddDate = resultset.getTimestamp("qAddDate");
				String AnswerStatus = resultset.getString("qAnswerStatus");
				String qAddDate = new SimpleDateFormat("yyy-MM-dd").format(AddDate);

				if (AnswerStatus == null) {
					qAnswerStatus = "미답변";
				} else {
					qAnswerStatus = AnswerStatus;
				}

				BDtoQnA dto = new BDtoQnA(qnACode, qAddDate, bNumber, qTitle, qAnswerStatus);
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
	
	
	public int searchQnACount(String searchtxt) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int searchQnACount = 0;
		String searchtxtCheck = null;
		if (searchtxt == null){
			searchtxtCheck = "";
		} else {
			searchtxtCheck = " like '%" + searchtxt + "%'";
			
		}

		try {
			connection = dataSource.getConnection();

			String query = "select bNumber, qTitle, qAddDate, qAnswerStatus "
					+ "from BnS as B join QnA as Q on B.Customer_cId = Q.Customer_cId "
					+ "where bNumber" + searchtxtCheck;

//			insert into Product (pPrice, pCategory, PExpirationDate, pName, pQuantity)
//			value ('2000', 'food', '2021-05-13', '콜라', '1');
			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				searchQnACount++;
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

		return searchQnACount;
	}
}
