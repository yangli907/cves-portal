package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import models.AbstractData;
import models.build.LocationData;

public class BuildDataProvider implements AbstractDataProvider {
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
					"jdbc:mysql://localhost/cves",
					"cves", "cves");
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			StringBuilder execStat = new StringBuilder("select * from cves.location");
			resultSet = statement
					.executeQuery(execStat.toString());
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
	public <T extends List<AbstractData>,U> T writeResultSet(ResultSet resultSet) throws SQLException, ParseException {
		T locationResultSet = (T) new ArrayList<AbstractData>();
		while (resultSet.next()) {
			LocationData location = new LocationData();
			location.setId(resultSet.getInt("id"));
			location.setUserId(resultSet.getInt("userid"));
			location.setTimestamp(resultSet.getDate("timestamp"));
			location.setLongitude(resultSet.getFloat("longitude"));
			location.setLatitude(resultSet.getFloat("latitude"));
			locationResultSet.add(location);
		}
		return locationResultSet;
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
