package com.example.e_com;

import java.sql.*;

public class DatabaseConnection {
    Connection con  = null;
    String SQLURL = "jdbc:mysql://localhost:3306/ecommerce?useSSL=false";
    String username = "root";
    String password = "123456";
     DatabaseConnection() throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.jdbc.Driver");

        con = DriverManager.getConnection(SQLURL,username,password);
        if(con != null) {
            System.out.println("Our Connection is Establishing");
        }
    }
    public ResultSet executeQuery(String query) {
         ResultSet result = null;
         try {
             Statement statement = con.createStatement();
             result =  statement.executeQuery(query);//resultset store the row
         }
       catch (Exception e)
       {
           e.printStackTrace();
       }
         return result;
    }
    public int updateQuery(String query){
         int rows = 0;
         try {
             Statement statement = con.createStatement();
             rows = statement.executeUpdate(query);
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
        return rows;
    }
}
