package com.flipkart.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionHelper {

//    private static Connection connection = null;
//
//    public static Connection getConnection() {
//
//        if (connection != null)
//            return connection;
//        else {
//            try {
//                Properties prop = new Properties();
//                InputStream inputStream = DBConnectionHelper.class.getClassLoader().getResourceAsStream("../configuration.properties");
//                prop.load(inputStream);
//                String driver = prop.getProperty("driver");
//                String url = prop.getProperty("url");
//                String user = prop.getProperty("user");
////                String url = "jdbc:mysql://localhost:3306";
////                String user = "root";
////                String password = "Nagni@1999wagat";
//                String password = prop.getProperty("password");
//                Class.forName(driver);
//                connection = DriverManager.getConnection(url, user, password);
//            }
//            catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return connection;
//        }
//
//
//    }

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306";
    private static final String user = "root";
    private static final String password = "Nagni@1999wagat";
    private static Connection connection = null;

    public static Connection getConnection() {

        if (connection != null)
            return connection;
        else {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                System.out.println(connection);
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Exception Thrown!");
                e.printStackTrace();
            }
            return connection;
        }

    }


}
