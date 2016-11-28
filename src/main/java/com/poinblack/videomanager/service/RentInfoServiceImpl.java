package com.poinblack.videomanager.service;

import com.poinblack.videomanager.dao.RentInfoDao;
import com.poinblack.videomanager.model.RentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("RentInfoService")
@Transactional
public class RentInfoServiceImpl implements RentInfoService{

    @Autowired
    RentInfoDao rentInfoDao;

    public int insertRentInfo(RentInfo rentInfo) {
        return rentInfoDao.insertRentInfo(rentInfo);
    }

    public List<RentInfo> selectAllRentInfo() {
        return rentInfoDao.selectAllRentInfo();
    }

    public RentInfo selectByRentInfo(int rental_id) {
        return rentInfoDao.selectByRentInfo(rental_id);
    }

    public void deleteByRentInfo(int rental_id) {
        rentInfoDao.deleteRentInfo(rental_id);
    }

    public void updateRentInfo(RentInfo rentInfo) {
        rentInfoDao.updateRentInfo(rentInfo);
    }
}
