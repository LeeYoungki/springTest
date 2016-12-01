package com.poinblack.videomanager.service;

import com.poinblack.videomanager.model.RentInfo;

import java.util.List;

public interface RentInfoService {
    int insertRentInfo(RentInfo rentInfo);

    List<RentInfo> selectAllRentInfo();

    RentInfo selectByRentInfo(int rentInfoId);

    void deleteByRentInfo(int rentInfoId);

    void updateRentInfo(RentInfo rentInfo);
}
