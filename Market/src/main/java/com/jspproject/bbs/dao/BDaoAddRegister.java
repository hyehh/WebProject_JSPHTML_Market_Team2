package com.jspproject.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BDaoAddRegister {
	DataSource dataSource;

	public BDaoAddRegister() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Market");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getCode() {
		int pCode = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;

		try {
			connection = dataSource.getConnection();

			String query = "select max(pCode) from Product";
			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				pCode = resultset.getInt("max(pCode)");
			}

			System.out.println("dao" + pCode);
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

		return pCode;
	}

	public void addRegister(int pCode, String sId) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();

			String query = "insert into Register  (Product_pCode, Seller_sId) "
					+ "value (?, ?)";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, pCode);
			preparedStatement.setString(2, sId);

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
