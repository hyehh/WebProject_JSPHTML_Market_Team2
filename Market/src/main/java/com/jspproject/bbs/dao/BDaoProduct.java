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

import com.jspproject.bbs.dto.BProductDto;

public class BDaoProduct {

	DataSource dataSource;
	
	public BDaoProduct() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Market");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteList(String pCode, String pDelete){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "update Product "
					+ "set pDelete = ?, pDeleteDate = now() "
					+ "where pCode = ?";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, pDelete);
			preparedStatement.setString(2, pCode);
			
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
	
	public void deleteRegister(String pCode) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();

			String query = "insert into Register (Product_pCode, Seller_sId, rDeleteDate) "
					+ "value ((select pCode from Product where pCode = ?), 'admin', now())";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, pCode);

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
	
	
	public ArrayList<BProductDto> list(int start, int end){
		ArrayList<BProductDto> dtos = new ArrayList<BProductDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String pStatus;
		
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select pCode, pCategory, pName, pPrice, pPriceDC, pExpirationDate, pStatus, pAddDate "
					+ "from Product where pDelete = 0 limit ?, ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				String pCode = resultset.getString("pCode");
				String pCategory = resultset.getString("pCategory");
				String pName = resultset.getString("pName");
				String pPrice = resultset.getString("pPrice");
				String pPriceDC = resultset.getString("pPriceDC");
				String pExpirationDate = resultset.getString("pExpirationDate");
				String Status = resultset.getString("pStatus");
				Timestamp AddDate = resultset.getTimestamp("pAddDate");
				String pAddDate = new SimpleDateFormat("yyyy-MM-dd").format(AddDate);
				
				
				if (Status.equals("판매중")) {
					pStatus = "판매중";
				} else {
					pStatus = "품절";
				}
				
				BProductDto dto = new BProductDto(pCode, pName, pPrice, pPriceDC, pExpirationDate, pStatus, pAddDate, pCategory);
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
	
	public int sales() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int salesCount = 0;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select * from Product where pDelete = 0";
			
			
//			insert into Product (pPrice, pCategory, PExpirationDate, pName, pQuantity)
//			value ('2000', 'food', '2021-05-13', '콜라', '1');
			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				salesCount++;
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
		
		return salesCount;
		
	}
	
	public int salesTrue() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int salesTure = 0;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select * from Product where pDelete = 0 and pStatus = '판매중'";
			
//			insert into Product (pPrice, pCategory, PExpirationDate, pName, pQuantity)
//			value ('2000', 'food', '2021-05-13', '콜라', '1');
			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				salesTure++;
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
		
		return salesTure;
		
	}
	
	public int salesFalse() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int salesFalse = 0;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select * from Product where pDelete = 0 and pStatus = '품절'";
			
//			insert into Product (pPrice, pCategory, PExpirationDate, pName, pQuantity)
//			value ('2000', 'food', '2021-05-13', '콜라', '1');
			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				salesFalse++;
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
		
		return salesFalse;
		
	}
	
	public ArrayList<BProductDto> search(String search, String searchtxt, int start, int end) {
		String searchCheck = null;
		String searchtxtCheck = null;
		
			if (search == null){
				searchCheck = "";
				searchtxtCheck = "";
			} else {
				searchCheck = search;
				searchtxtCheck = " like '%" + searchtxt + "%'";
				
			}
		
		String whereStatement = "select pCode, pCategory, pName, pPrice, pPriceDC, pExpirationDate, pProductEA, pStatus, pAddDate \n"
				+ "from Product where pDelete = 0" + searchCheck + searchtxtCheck + " limit ?, ?";
		
		System.out.println(whereStatement);
		
		ArrayList<BProductDto> dtos = new ArrayList<BProductDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String pStatus;
		
		try {
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(whereStatement);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				String pCode = resultset.getString("pCode");
				String pCategory = resultset.getString("pCategory");
				String pName = resultset.getString("pName");
				String pPrice = resultset.getString("pPrice");
				String pPriceDC = resultset.getString("pPriceDC");
				String pExpirationDate = resultset.getString("pExpirationDate");
				String Status = resultset.getString("pStatus");
				Timestamp AddDate = resultset.getTimestamp("pAddDate");
				String pAddDate = new SimpleDateFormat("yyyy-MM-dd").format(AddDate);
				
				
				if (Status.equals("판매중")) {
					pStatus = "판매중";
				} else {
					pStatus = "품절";
				}
				
				BProductDto dto = new BProductDto(pCode, pName, pPrice, pPriceDC, pExpirationDate, pStatus, pAddDate, pCategory);
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
	
	public ArrayList<BProductDto> salesTureList(int start, int end){
		ArrayList<BProductDto> dtos = new ArrayList<BProductDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String pStatus;
		
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select pCode, pCategory, pName, pPrice, pPriceDC, pExpirationDate, pStatus, pAddDate from Product "
					+ "where pDelete = 0 and pStatus = '판매중' limit ?, ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				String pCode = resultset.getString("pCode");
				String pCategory = resultset.getString("pCategory");
				String pName = resultset.getString("pName");
				String pPrice = resultset.getString("pPrice");
				String pPriceDC = resultset.getString("pPriceDC");
				String pExpirationDate = resultset.getString("pExpirationDate");
				String Status = resultset.getString("pStatus");
				Timestamp AddDate = resultset.getTimestamp("pAddDate");
				String pAddDate = new SimpleDateFormat("yyyy-MM-dd").format(AddDate);
				
				
				if (Status.equals("판매중")) {
					pStatus = "판매중";
				} else {
					pStatus = "품절";
				}
				
				BProductDto dto = new BProductDto(pCode, pName, pPrice, pPriceDC, pExpirationDate, pStatus, pAddDate, pCategory);
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
	
	
	public ArrayList<BProductDto> salesFalseList(int start, int end){
		ArrayList<BProductDto> dtos = new ArrayList<BProductDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String pStatus;
		
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select pCode, pCategory, pName, pPrice, pPriceDC, pExpirationDate, pProductEA, pStatus, pAddDate from Product "
					+ "where pDelete = 0 and pStatus = '품절' limit ?, ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				String pCode = resultset.getString("pCode");
				String pCategory = resultset.getString("pCategory");
				String pName = resultset.getString("pName");
				String pPrice = resultset.getString("pPrice");
				String pPriceDC = resultset.getString("pPriceDC");
				String pExpirationDate = resultset.getString("pExpirationDate");
				String Status = resultset.getString("pStatus");
				Timestamp AddDate = resultset.getTimestamp("pAddDate");
				String pAddDate = new SimpleDateFormat("yyyy-MM-dd").format(AddDate);
				
				
				if (Status.equals("판매중")) {
					pStatus = "판매중";
				} else {
					pStatus = "품절";
				}
				
				BProductDto dto = new BProductDto(pCode, pName, pPrice, pPriceDC, pExpirationDate, pStatus, pAddDate, pCategory);
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
	
	
	public BProductDto selectProduct(String selecCode) {
		BProductDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String pProductEA = null;
		String pStatus = null;
		int selectProductBuyCount = this.selectProductBuyCount(selecCode);
		
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select pCode, pCategory, pName, pPrice, pPriceDC, pExpirationDate, pProductEA, pStatus, pAddDate, pFilePath "
					+ "from Product where pDelete = 0 and pCode = ?";
			System.out.println("다오실험1");
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(selecCode));
			System.out.println(query);
			
			resultset = preparedStatement.executeQuery();
//			select pCode, pCategory, pName, pPrice, pExpirationDate, pQuantity, pStatus from Product
			if (resultset.next()) {
				String pCode = resultset.getString("pCode");
				String pCategory = resultset.getString("pCategory");
				String pName = resultset.getString("pName");
				String pPrice = resultset.getString("pPrice");
				String pPriceDC = resultset.getString("pPriceDC");
				String pExpirationDate = resultset.getString("pExpirationDate");
				String ProductEA = resultset.getString("pProductEA");
				String Status = resultset.getString("pStatus");
				Timestamp AddDate = resultset.getTimestamp("pAddDate");
				String pAddDate = new SimpleDateFormat("yyyy-MM-dd").format(AddDate);
				String pFilePath = resultset.getString("pFilePath");
				
				int eaInt = Integer.parseInt(ProductEA);
				pProductEA = Integer.toString(eaInt - selectProductBuyCount);
				
				
				if (Status.equals("판매중")) {
					pStatus = "판매중";
				} else {
					pStatus = "품절";
				}
				dto = new BProductDto(pCode, pCategory, pName, pPrice, pPriceDC, pExpirationDate, pProductEA, pStatus, pAddDate, pFilePath);
				
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
	
	
	public int selectProductBuyCount(String selecCode) {
		BProductDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int selectProductBuyCount = 0;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select sum(bQuantity) from BnS where bBuyCancelDate is null and Product_pCode = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(selecCode));
			System.out.println(query);
			
			resultset = preparedStatement.executeQuery();
//			select pCode, pCategory, pName, pPrice, pExpirationDate, pQuantity, pStatus from Product
			while (resultset.next()) {
				selectProductBuyCount = resultset.getInt("sum(bQuantity)");
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
		return selectProductBuyCount;
	}
	
	
	
	
//	pCode, pCategory, pName, pPrice, pExpirationDate, pQuantity, pStatus
	public void productModify(String pCode, String pCategory, String pName, String pPriceDC ,String pProductEA, String pStatus) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		System.out.println("커맨드가문제에요?");
		
		try {
			connection = dataSource.getConnection();
			
			String query = "update Product "
					+ "set pCategory = ?, pName = ?, pPriceDC = ?, "
					+ "pProductEA = ?, pStatus = ? "
					+ "where pCode = ?";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, pCategory);
			preparedStatement.setString(2, pName);
			preparedStatement.setString(3, pPriceDC);
			preparedStatement.setString(4, pProductEA);
			preparedStatement.setString(5, pStatus);
			preparedStatement.setString(6, pCode);
			
			preparedStatement.executeUpdate();
			
			System.out.println(query);
			
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
	
	
	public void upDateRegister(String pCode) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();

			String query = "insert into Register (Product_pCode, Seller_sId, rEditDate) "
					+ "value ((select pCode from Product where pCode = ?), 'admin', now())";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, pCode);

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
	
		public int getSearchCount(String search, String searchtxt) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultset = null;
			int getSearchCount = 0;
			String searchCheck = null;
			String searchtxtCheck = null;
			
				if (search == null){
					searchCheck = "";
					searchtxtCheck = "";
				} else {
					searchCheck = search;
					searchtxtCheck = " like '%" + searchtxt + "%'";
					
				}
			
			try {
				connection = dataSource.getConnection();
				
				String query = "select pCode, pCategory, pName, pPrice, pPriceDC, pExpirationDate, pProductEA, pStatus, pAddDate "
						+ "from Product where pDelete = 0" + searchCheck + searchtxtCheck;
				
				preparedStatement = connection.prepareStatement(query);
				resultset = preparedStatement.executeQuery();
				
				while (resultset.next()) {
					getSearchCount++;
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
			
			return getSearchCount;
		}
	
	
} //------끝

