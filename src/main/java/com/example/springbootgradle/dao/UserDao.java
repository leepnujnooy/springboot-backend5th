package com.example.springbootgradle.dao;

import com.example.springbootgradle.domain.User;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class UserDao {

    public void add(User user) throws ClassNotFoundException, SQLException {
        Map<String,String> env = getenv();
        String dbHost = env.get("DB_HOST"); //DB_HOST=jdbc:mysql://localhost:3306/spring-db . 스키마까지도 연결해줘야함
        String dbUser = env.get("DB_USER"); //DB_USER=루트
        String dbPassword = env.get("DB_PASSWORD"); //DB_PASSWORD=패스워드



        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost,dbUser,dbPassword);


        PreparedStatement ps = conn.prepareStatement("insert into user(id,name,password) values(?,?,?)");
        ps.setString(1,user.getId());
        ps.setString(2,user.getName());
        ps.setString(3,user.getPassword());

        ps.executeUpdate();
        ps.close();
        conn.close();


    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Map<String,String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");



        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost,dbUser,dbPassword);



        PreparedStatement ps = conn.prepareStatement("select id,name,password from user where id = ?");
        //?에 값을 대입하는 것이 setString
        ps.setString(1,id);
        ResultSet rs = ps.executeQuery();
        rs.next(); //컨트롤 엔터한거. 줄바꿈한거랑 같음

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));


        //영업종료했는데 손님이 남아있는 격임. 꼭 클로즈해줘야함
        rs.close();
        ps.close();
        conn.close();


        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
//        User user = new User();

        //set
//        user.setId("2");
//        user.setName("준필");
//        user.setPassword("pqweie11");


        //userDao.add(user);

        User selectedUser = userDao.get("1");
        System.out.println(selectedUser.getId());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword());
    }
}
