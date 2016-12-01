package com.poinblack.videomanager.service;

import com.poinblack.videomanager.model.Video;

import java.util.List;
import java.util.Map;

public interface VideoService {
    int LATE_STANDARD = 1;

    void insertVideo(Video video);

    List<Video> selectAllVideo();

    List<Video> selectRentVideo();

    void deleteByVideoId(int videoId);

    Video selectByVideoId(int videoId);

    void updateVideo(Video video);

    Map<String,Object> rentVideo(int videoId, int userId);

    Map<String,Object> returnVideo(int videoId, int userId, int rentInfoId);
}
