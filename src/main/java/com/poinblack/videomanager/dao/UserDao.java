package com.poinblack.videomanager.dao;

import com.poinblack.videomanager.model.User;

import java.util.List;

public interface UserDao {
    void insertUser(User user);

    List<User> selectAllUser();

    void deleteUser(int id);

    User selectByUser(int id);

    void updateUser(User user);
}
