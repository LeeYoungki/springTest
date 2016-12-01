package com.poinblack.videomanager.dao;

import com.poinblack.videomanager.model.RentInfo;
import com.poinblack.videomanager.model.User;

import java.util.List;

public interface RentInfoDao {
    int insertRentInfo(RentInfo rentInfo);

    List<RentInfo> selectAllRentInfo();

    void deleteRentInfo(int id);

    RentInfo selectByRentInfo(int id);

    void updateRentInfo(RentInfo rentInfo);

    List<RentInfo> selectByUser(User user);
}
