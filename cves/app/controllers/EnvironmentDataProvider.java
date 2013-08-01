package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.AbstractData;
import models.environment.EnvironmentData;

public class EnvironmentDataProvider implements AbstractDataProvider {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	/* (non-Javadoc)
	 * @see controllers.AbstractDataProvider#readDataBase()
	 */
	@Override
	public <T extends List<AbstractData>,U> T readDataBase(HashMap<String,String> filter) throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection(
					"jdbc:mysql://petools02.phx.qa.ebay.com:8080/cdaas_dev",
					"cassiniqe", "cdaas");
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement
					.executeQuery("select * from cdaas_dev.environment");
			return writeResultSet(resultSet);
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	/* (non-Javadoc)
	 * @see controllers.AbstractDataProvider#writeResultSet(java.sql.ResultSet)
	 */
	@Override
	public <T extends List<AbstractData>,U> T writeResultSet(ResultSet resultSet) throws SQLException {
		T envList = (T) new ArrayList<AbstractData>();
		while (resultSet.next()) {
			EnvironmentData env = new EnvironmentData();
			env.setDesc(resultSet.getString("DESCRIPTION"));
			env.setModifiedBy(resultSet.getString("MODIFIED_BY"));
			env.setIsActive(resultSet.getInt("IS_ACTIVE"));
			env.setIsInUse(resultSet.getInt("IS_IN_USE"));
			env.setName(resultSet.getString("NAME"));
			env.setModificationTime(resultSet.getTimestamp("MODIFICATION_TIME"));
			envList.add(env);
		}
		return envList;
	}

	// You need to close the resultSet
	/* (non-Javadoc)
	 * @see controllers.AbstractDataProvider#close()
	 */
	@Override
	public void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
