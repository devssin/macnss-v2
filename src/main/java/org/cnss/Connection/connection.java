package org.cnss.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class connection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cnss2";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
}
