package com.poinblack.videomanager.dao;

import com.poinblack.videomanager.model.Video;

import java.util.List;

public interface VideoDao {
    void insertVideo(Video video);

    List<Video> selectAllVideo();

    List<Video> selectRentVideo(String state);

    void deleteVideo(int id);

    Video selectByVideo(int id);

    void updateVideo(Video video);
}
