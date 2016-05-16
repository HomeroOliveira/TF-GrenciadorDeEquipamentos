package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBase {

	private static final String USER = "";
	private static final String PASSWORD = "";

	public static Connection getConnection() throws SQLException {
		return DriverManager
				   .getConnection("jdbc://thin//camburi.pucrs.br", USER, PASSWORD);
	}
	
}
