package com.example.springbootgradle.db;

import java.sql.*;
import java.util.Scanner;

public class ConnectChecker {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectChecker cc = new ConnectChecker();
        cc.select();

    }

    public void check() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring-db","root","password");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("show databases");
        rs = st.getResultSet();
        while(rs.next()){
            String str = rs.getString(1);
            System.out.println(str);
        }
    }

    public void add() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring-db","root","password");
        PreparedStatement pstmt = conn.prepareStatement("insert into users(id,name,password) values(?,?,?)");
        pstmt.setString(1,"2");
        pstmt.setString(2,"sion");
        pstmt.setString(3,"1053563");
        pstmt.executeUpdate();
    }

    public void select() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring-db","root","password");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from users");
        while(rs.next()){
            String str = rs.getString(1);
            String str2 = rs.getString(2);
            String str3 = rs.getString(3);

            System.out.println(str + str2 + str3);
        }
    }



}
