package org.academiadecodigo.vim4cache.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by codecadet on 31/03/17.
 */
public class ConnectionManager {
    ////No need to use with hibernate
    Connection connection = null;

    public Connection getConnection() {

        try {
            if (connection == null) {
                connection = DriverManager.getConnection("transaction:mysql://localhost:3306/score","root", "" );
            }
        } catch (SQLException ex) {
            System.out.println("Failure to connect to database : " + ex.getMessage());
        }
        return connection;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Failure to close database connections: " + ex.getMessage());
        }
    }
}