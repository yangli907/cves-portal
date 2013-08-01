package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import models.AbstractData;

public interface AbstractDataProvider {

	public abstract <T extends List<AbstractData>, U> T readDataBase(HashMap<String,String> filter)
			throws Exception;

	public abstract <T extends List<AbstractData>, U> T writeResultSet(
			ResultSet resultSet) throws SQLException, ParseException;

	// You need to close the resultSet
	public abstract void close();

}