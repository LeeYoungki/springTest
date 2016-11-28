package com.poinblack.videomanager.service;

import com.poinblack.videomanager.model.Video;

import java.util.List;
import java.util.Map;

public interface VideoService {
    int late_standard = 2;

    void insertVideo(Video video);

    List<Video> selectAllVideo();

    List<Video> selectRentVideo();

    void deleteByVideoId(int video_id);

    Video selectByVideoId(int video_id);

    void updateVideo(Video video);

    Map<String,Object> rentVideo(int video_id, String user_id);

    Map<String,Object> returnVideo(int video_id, String user_id , int rentInfo_id);
}
