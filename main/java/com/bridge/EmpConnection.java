package com.bridge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmpConnection {

    Connection getConnection() {
        String URL = "jdbc:mysql://localhost:3306/payroll_services";
        String USERNAME = "root";
        String PASSWORD = "password";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers found");
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Connection successfull!");
        } catch (ClassNotFoundException e) {
            throw new EmpException("invalid driver");
         } catch ( SQLException e) {
            throw new EmpException("invalid get connection parameters");
        }
        return connection;
    }
}
