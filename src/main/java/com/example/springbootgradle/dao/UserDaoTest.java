package com.example.springbootgradle.dao;

import com.example.springbootgradle.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        User user = new User();

        user.setId("25");
        user.setName("용록");
        user.setPassword("경록이형동생");
        userDao.add(user);


    }
}
