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
import models.build.BuildData;

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
					"jdbc:mysql://petools02.phx.qa.ebay.com:8080/cdaas_dev",
					"cassiniqe", "cdaas");
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			StringBuilder execStat = new StringBuilder("select * from cdaas.cdaas_version_history where build_timestamp>'2013-01-07_13-01-06'");
			if(filter.get("component")!=null){
				execStat.append(" and build_type='"+filter.get("component")+"'");
			}
			execStat.append(" order by build_timestamp DESC limit 200");
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
		T buildList = (T) new ArrayList<AbstractData>();
		while (resultSet.next()) {
			BuildData build = new BuildData();
			build.setRun_id(resultSet.getInt("run_id"));
			build.setBuild_type(resultSet.getString("build_type"));
			build.setBuild_number(resultSet.getString("build_number"));
			build.setCommit_id(resultSet.getString("commit_id"));
			Date date = resultSet.getTimestamp("build_timestamp");
			String dateString = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a").format(date);
			build.setBuild_timestamp(dateString);
			build.setRegression_status(resultSet.getInt("regression_status"));
			String repoUrl = resultSet.getString("repo_url");
			String[] tmpRepoArray = repoUrl.split("/");
			String repoName = tmpRepoArray[tmpRepoArray.length-1];
			build.setRepository(repoName.subSequence(0, repoName.length()-4).toString());
			build.setDeploy_to_preprod(resultSet.getInt("deployed_to_preprod"));
			build.setDeploy_to_prodn(resultSet.getInt("deployed_to_prodn"));
			build.setDeploy_to_staging(resultSet.getInt("deployed_to_staging"));
			buildList.add(build);
		}
		return buildList;
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
