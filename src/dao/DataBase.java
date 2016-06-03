package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 
class DataBase {

    private static final String USER = "junior";
    private static final String PASSWORD = "2003";
    //private static final String URL = "jdbc:oracle:thin:@//camburi.pucrs.br:1521/facin11g";
    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/XE";

    public static Connection getConnection() throws SQLException {
        return DriverManager
                .getConnection(URL, USER, PASSWORD);
    }

}
