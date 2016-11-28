package com.poinblack.videomanager.service;

import com.poinblack.videomanager.model.User;

import java.util.List;

public interface UserService {
    void insertUser(User user);

    List<User> selectAllUser();

    List<User> selectAllSortingUser();

    void deleteByUserId(String user_id);

    User selectByUserId(String user_id);

    void updateUser(User user);
}
