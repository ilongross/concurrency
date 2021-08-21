package com.ilongross.concurrency.chapter_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Использование класс ThreadLocal для ограничения одним потоком

public class SafeThreadLocal {

    private static ThreadLocal<Connection> connectionHolder =
            new ThreadLocal<Connection>(){
                public Connection initialValue() {
                    Connection conn = null;
                    try {
                        conn = DriverManager.getConnection("");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return conn;
                }
            };

    public static Connection getConnection() {
        return connectionHolder.get();
    }
}
