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

import java.text.ParseException;
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
        video.setRent_fee(Integer.parseInt(env.getProperty("rentInfo."+video.getType()+"_fee")));
        video.setLate_fee(Integer.parseInt(env.getProperty("lateInfo."+video.getType()+"_fee")));
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

    public void deleteByVideoId(int video_id) {
        videoDao.deleteVideo(video_id);
    }

    public Video selectByVideoId(int video_id) {
        return videoDao.selectByVideo(video_id);
    }

    public void updateVideo(Video video) {
        videoDao.updateVideo(video);
    }

    public Map<String, Object> rentVideo(int video_id, String user_id) {
        Video video = videoDao.selectByVideo(video_id);

        if(isRentState(video)){
            return returnMessage(video);
    }
        video.setState(env.getProperty("state.rent"));

        videoDao.updateVideo(video);

        return saveRentInfo(user_id,video.getVideo_id());
    }

    public Map<String, Object> returnVideo(int video_id, String user_id,int rentInfo_id) {
        Video video = videoDao.selectByVideo(video_id);

        video.setState(env.getProperty("state.idle"));

        videoDao.updateVideo(video);
        return saveCashBook(user_id,video_id,rentInfo_id);
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

    private Map<String,Object> saveRentInfo(String user_id , int video_id){
        Map<String,Object> map = new HashMap<>();
        RentInfo rentInfo = buildRentInfo(user_id,video_id);

        int rentInfo_id = rentInfoDao.insertRentInfo(rentInfo);
        saveCashBook(user_id , video_id , rentInfo_id);

        //view 꾸미기용
        map.put("userName" , rentInfo.getUser().getUser_id());
        map.put("fee" , rentInfo.getFee());

        //Todo hibernate 활용방법 구현 시 삭제
        User user = userDao.selectByUser(user_id);
        user.addRentFee(rentInfo.getFee());
        userDao.updateUser(user);

        return map;
    }

    private Map<String,Object> saveCashBook(String user_id, int video_id,int rentInfo_id){

        CashBook cashBook= buildCashBook(user_id,video_id,rentInfo_id);

        cashBookDao.insertCashBook(cashBook);

        //view 꾸미기용
        Map<String,Object> map = new HashMap<>();
        map.put("userName" , cashBook.getUser().getUser_id());
        map.put("fee" , cashBook.getLate_fee());

        //Todo hibernate 활용방법 구현 시 삭제
        User user = userDao.selectByUser(user_id);
        user.addLateFee(cashBook.getLate_fee());
        userDao.updateUser(user);

        return map;
    }

    private RentInfo buildRentInfo(String user_id, int video_id){
        RentInfo rentInfo = new RentInfo();

        Video video = videoDao.selectByVideo(video_id);
        User user = userDao.selectByUser(user_id);

        rentInfo.setVideo(video);
        rentInfo.setUser(user);
        rentInfo.setFee(video.getRent_fee());
        rentInfo.setRent_date(new Date());
        rentInfo.setState(video.getState());

        return rentInfo;
    }

    private CashBook buildCashBook(String user_id, int video_id, int rentInfo_id){
        CashBook cashBook = new CashBook();

        Video video = videoDao.selectByVideo(video_id);
        User user = userDao.selectByUser(user_id);
        RentInfo rentInfo = rentInfoDao.selectByRentInfo(rentInfo_id);

        //Todo hibernate 활용방법 구현 시 변경
        int lateDate = (int) doDiffOfDate(rentInfo);

        int lateFee = 0;

        if(lateDate > late_standard){
            lateFee = video.getLate_fee()*(lateDate-late_standard);
        }

        cashBook.setUser(user);
        cashBook.setVideo(video);
        cashBook.setRent_fee(video.getRent_fee());
        cashBook.setLate_fee(lateFee);
        cashBook.setTotal(video.getRent_fee() + lateFee);

        return cashBook;
    }
    //Todo hibernate 활용방법 구현 시 변경 혹은 삭제
    public long doDiffOfDate(RentInfo rentInfo){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date beginDate =formatter.parse(String.valueOf(rentInfo.getRent_date()));
            Date endDate = new Date();

            // 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
            long diff = endDate.getTime() - beginDate.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);

            return diffDays;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
