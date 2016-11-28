package com.poinblack.videomanager.dao;

import com.poinblack.videomanager.model.User;

import java.util.List;

public interface UserDao {
    void insertUser(User user);

    List<User> selectAllUser();

    void deleteUser(String user_id);

    User selectByUser(String user_id);

    void updateUser(User user);
}
