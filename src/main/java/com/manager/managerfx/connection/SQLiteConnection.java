package com.manager.managerfx.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {

    public static Connection connect() {

        try {
            String path = System.getProperty("user.dir") + "/src/main/resources/com/manager/managerfx/db/manager.db";
            String url = "jdbc:sqlite:" + path;
            return DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
