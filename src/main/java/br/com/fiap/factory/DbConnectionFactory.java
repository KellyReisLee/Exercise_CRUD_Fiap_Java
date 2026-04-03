package br.com.fiap.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionFactory {

    public static Connection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@//oracle.fiap.com.br:1521/orcl"
                ,System.getenv("DB_User"),
                 System.getenv("DB_Pass"));

        return connection;

    }
}
