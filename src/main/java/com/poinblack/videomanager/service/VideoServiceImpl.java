package com.poinblack.videomanager.service;

import com.poinblack.videomanager.dao.CashBookDao;
import com.poinblack.videomanager.dao.RentInfoDao;
import com.poinblack.videomanager.dao.UserDao;
import com.poinblack.videomanager.dao.VideoDao;
import com.poinblack.videomanager.model.CashBook;
import com.poinblack.videomanager.model.RentInfo;
import com.poinblack.videomanager.model.User;
import com.poinblack.videomanager.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("VideoService")
@Transactional
@PropertySource("classpath:rentinfo.properties")
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoDao videoDao;

    @Autowired
    UserDao userDao;

    @Autowired
    RentInfoDao rentInfoDao;

    @Autowired
    CashBookDao cashBookDao;

    @Autowired
    Environment env;

    public void insertVideo(Video video) {
        video.setRentFee(Integer.parseInt(env.getProperty("rentInfo."+video.getType()+"_fee")));
        video.setLateFee(Integer.parseInt(env.getProperty("lateInfo."+video.getType()+"_fee")));
        video.setState(env.getProperty("state.idle"));

        videoDao.insertVideo(video);
    }

    public List<Video> selectAllVideo() {
        return videoDao.selectAllVideo();
    }

    public List<Video> selectRentVideo() {
        List<Video> videos = videoDao.selectRentVideo(env.getProperty("state.rent"));

        return videos;
    }

    public void deleteByVideoId(int videoId) {
        videoDao.deleteVideo(videoId);
    }

    public Video selectByVideoId(int videoId) {
        return videoDao.selectByVideo(videoId);
    }

    public void updateVideo(Video video) {
        videoDao.updateVideo(video);
    }

    public Map<String, Object> rentVideo(int videoId, int userId) {
        Video video = videoDao.selectByVideo(videoId);

        if(isRentState(video)){
            return returnMessage(video);
    }
        video.setState(env.getProperty("state.rent"));

        videoDao.updateVideo(video);

        return saveRentInfo(userId,video.getId());
    }

    public Map<String, Object> returnVideo(int videoId, int userId, int rentInfoId) {
        Video video = videoDao.selectByVideo(videoId);
        RentInfo rentInfo = rentInfoDao.selectByRentInfo(rentInfoId);

        rentInfo.setState(env.getProperty("state.idle"));
        video.setState(env.getProperty("state.idle"));

        rentInfoDao.updateRentInfo(rentInfo);
        videoDao.updateVideo(video);
        return saveCashBook(userId, videoId, rentInfoId);
    }

    private boolean isRentState(Video video){
        if(video.getState().equals(env.getProperty("state.rent"))){
            return true;
        }
        return false;
    }

    private Map<String,Object> returnMessage(Video video){
        Map<String,Object> map = new HashMap<>();
        map.put("msg" , "이미 빌려간 비디오입니다.");
        return map;
    }

    private Map<String,Object> saveRentInfo(int userId, int videoId){
        Map<String,Object> map = new HashMap<>();
        RentInfo rentInfo = buildRentInfo(userId, videoId);

        int rentInfo_id = rentInfoDao.insertRentInfo(rentInfo);
        saveCashBook(userId, videoId, rentInfo_id);

        //view 꾸미기용
        map.put("userName" , rentInfo.getUser().getUserId());
        map.put("fee" , rentInfo.getFee());

        //Todo hibernate 활용방법 구현 시 삭제
        User user = userDao.selectByUser(userId);
        user.addRentFee(rentInfo.getFee());
        userDao.updateUser(user);

        return map;
    }

    private Map<String,Object> saveCashBook(int userId, int videoId, int rentInfoId){

        CashBook cashBook = cashBookDao.selectByRentInfo(rentInfoId);

        if(cashBook == null){
            System.out.println("buildCashBook");
            cashBook = buildCashBook(userId, videoId, rentInfoId);
            cashBookDao.insertCashBook(cashBook);
        }
        else{
            cashBook.setLateFee(lateFeeCal(cashBook.getRentInfo() , cashBook.getVideo()));
            cashBookDao.updateCashBook(cashBook);
        }

        //view 꾸미기용
        Map<String,Object> map = new HashMap<>();
        map.put("userName" , cashBook.getUser().getUserId());
        map.put("fee" , cashBook.getLateFee());

        //Todo hibernate 활용방법 구현 시 삭제
        User user = userDao.selectByUser(userId);
        user.addLateFee(cashBook.getLateFee());
        userDao.updateUser(user);

        return map;
    }

    private RentInfo buildRentInfo(int userId, int videoId){
        RentInfo rentInfo = new RentInfo();

        Video video = videoDao.selectByVideo(videoId);
        User user = userDao.selectByUser(userId);

        rentInfo.setVideo(video);
        rentInfo.setUser(user);
        rentInfo.setFee(video.getRentFee());
        rentInfo.setRentDate(new Date());
        rentInfo.setState(video.getState());

        return rentInfo;
    }

    private CashBook buildCashBook(int userId, int videoId, int rentInfoId){
        CashBook cashBook = new CashBook();

        Video video = videoDao.selectByVideo(videoId);
        User user = userDao.selectByUser(userId);
        RentInfo rentInfo = rentInfoDao.selectByRentInfo(rentInfoId);

        int lateFee = lateFeeCal(rentInfo,video);

        cashBook.setUser(user);
        cashBook.setVideo(video);
        cashBook.setRentInfo(rentInfo);
        cashBook.setRentFee(video.getRentFee());
        cashBook.setLateFee(lateFee);
        cashBook.setTotal(video.getRentFee() + lateFee);

        return cashBook;
    }

    private int lateFeeCal(RentInfo rentInfo , Video video){
        //Todo hibernate 활용방법 구현 시 변경
        int lateDate = (int) doDiffOfDate(rentInfo);
        int lateFee = 0;

        if(lateDate > LATE_STANDARD){
            lateFee = video.getLateFee()*(lateDate-LATE_STANDARD);
        }
        return lateFee;
    }
    //Todo hibernate 활용방법 구현 시 변경 혹은 삭제
    public long doDiffOfDate(RentInfo rentInfo){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date beginDate =rentInfo.getRentDate();
            Date endDate = new Date();

            // 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
            long diff = endDate.getTime() - beginDate.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.println("diffDays : "+diffDays);

            return diffDays;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
