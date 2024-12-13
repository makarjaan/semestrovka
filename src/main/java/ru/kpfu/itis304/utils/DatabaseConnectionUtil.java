package ru.kpfu.itis304.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnectionUtil {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConnectionUtil.class);

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
                log.error("jdbc:postgresql://%s:%s/%s"
                        .formatted(System.getenv("PROD_DB_HOST"),
                                        System.getenv("PROD_DB_PORT"), System.getenv("PROD_DB_PORT"),
                                System.getenv("PROD_DB_NAME")));
                log.error("%s".formatted(System.getenv("PROD_DB_USERNAME")));
                log.error("%s".formatted(System.getenv("PROD_DB_PASSWORD")));
                log.error("ошибочка {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

}