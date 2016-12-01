package com.poinblack.videomanager.service;

import com.poinblack.videomanager.dao.UserDao;
import com.poinblack.videomanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao dao;

    public void insertUser(User user) {
        dao.insertUser(user);
    }

    public List<User> selectAllUser() {
        return dao.selectAllUser();
    }

    public List<User> selectAllSortingUser() {
        List<User> users = dao.selectAllUser();

        Collections.sort(users , new RentFeeDescCompare());

        return users;
    }

    public void deleteByUserId(int userId) {
        dao.deleteUser(userId);
    }

    public User selectByUserId(int userId) {
        return dao.selectByUser(userId);
    }

    public void updateUser(User user) {
        dao.updateUser(user);
    }

    class RentFeeDescCompare implements Comparator<User> {

        @Override
        public int compare(User arg0, User arg1) {
            int value1 = arg0.getRentFee() + arg0.getLateFee();
            int value2 = arg1.getRentFee() + arg1.getLateFee();

            return value1 > value2 ? -1 : value1 < value2 ? 1:0;
        }

    }
}
