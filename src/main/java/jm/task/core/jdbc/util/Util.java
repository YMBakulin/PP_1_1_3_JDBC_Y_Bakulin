package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String connectionURL = "jdbc:mysql://localhost:3306/db_pp1134hib";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rootroot";

    private Connection connection = null;

    public Util() {

    }

    public Connection getConnection() {

            try {
                connection = DriverManager.getConnection(connectionURL, USERNAME, PASSWORD);
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        return connection;
    }
}
