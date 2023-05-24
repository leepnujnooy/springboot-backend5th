package com.example.springbootgradle.dao;

import com.example.springbootgradle.domain.User;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class UserDao {

    ConnectionMaker connectionMaker;


    //불필요한 의존관계를 제거하였음
    //생성자를 이용한 DI
    public UserDao(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {

        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement("insert into user(id,name,password) values(?,?,?)");
        ps.setString(1,user.getId());
        ps.setString(2,user.getName());
        ps.setString(3,user.getPassword());

        ps.executeUpdate();
        ps.close();
        conn.close();


    }

    public User get(String id) throws ClassNotFoundException, SQLException {


        Connection conn = connectionMaker.makeConnection();

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
        ConnectionMaker cm = new DConnectionMaker();
        UserDao userDao = new UserDao(cm);
        User user = new User();

        //add user to table

        user.setId("3");
        user.setName("맹구");
        user.setPassword("1905985");
        userDao.add(user);

        //템플릿메소드패턴
    }
}
