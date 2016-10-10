package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 
class DataBase {

    private static final String USER = "root";
    private static final String PASSWORD = "???";
    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/XE";

    static Connection getConnection() throws SQLException {
        return DriverManager
                .getConnection(URL, USER, PASSWORD);
    }

}
