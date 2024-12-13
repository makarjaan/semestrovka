package ru.kpfu.itis304.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnectionUtil {

    private DatabaseConnectionUtil() {}

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://%s:%s/%s"
                                .formatted(System.getenv("PROD_DB_HOST"),

                                        System.getenv("PROD_DB_PORT"),
                                        System.getenv("PROD_DB_NAME"))
                        ,"%s".formatted(System.getenv("PROD_DB_USERNAME")),
                        "%s".formatted(System.getenv("PROD_DB_PASSWORD"))
                );
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

}