package com.example.springbootgradle.db;

import java.sql.*;

public class ConnectTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectTest ct = new ConnectTest();
        ct.addData();
    }

    public void addData() throws ClassNotFoundException, SQLException {
        //jdbc 드라이버를 사용하기위한 코드
        Class.forName("com.mysql.cj.jdbc.Driver");

        //테이블에 접근하기위한 객체를 생성하는 코드
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","password");

        //SQL 쿼리문을 작성하는 코드
        PreparedStatement ps = conn.prepareStatement("insert into user(id,name,password) values(?,?,?)");
        ps.setString(1,"1");
        ps.setString(2,"제이필");
        ps.setString(3,"비밀번호");
        ps.executeUpdate();

        ps.close();
        conn.close();



    }
}
