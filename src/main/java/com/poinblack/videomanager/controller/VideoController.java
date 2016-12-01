package com.poinblack.videomanager.controller;

import com.poinblack.videomanager.model.RentInfo;
import com.poinblack.videomanager.model.User;
import com.poinblack.videomanager.model.Video;
import com.poinblack.videomanager.service.RentInfoService;
import com.poinblack.videomanager.service.UserService;
import com.poinblack.videomanager.service.VideoService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private RentInfoService rentInfoService;

    @RequestMapping(value = "insertVideo")
    public String insertVideo() {
        return "insertVideo";
    }

    @RequestMapping(value = "joinVideo")
    public String joinVideo(@RequestParam Map<String , Object> map) {
        Video video = new Video();
        video.setTitle((String) map.get("title"));
        video.setType((String) map.get("type"));
        video.setVid((String) map.get("vid"));

        videoService.insertVideo(video);

        return "main";
    }

    @RequestMapping(value = "videoList")
    public String videoList(@RequestParam String userId,Model model) {
        List<Video> videos = videoService.selectAllVideo();
        model.addAttribute("videos" , videos);
        model.addAttribute("userId" , userId);

        return "videoList";
    }

    @RequestMapping(value = "rentVideo")
    public String rentVideo(@RequestParam String videoId ,
                            @RequestParam String userId ,
                            Model model) {

        //view 꾸미기용 map
        Map<String ,Object> map = new HashedMap();
        map = videoService.rentVideo(Integer.parseInt(videoId), Integer.parseInt(userId));

        model.addAttribute("userName" , map.get("userName"));
        model.addAttribute("fee" , map.get("fee"));
        model.addAttribute("msg" , map.get("msg"));

        return "payment";
    }

    @RequestMapping(value = "selectReturnVideo")
    public String selectReturnVideo(Model model) {
        List<Video> videos = videoService.selectRentVideo();
        model.addAttribute("videos" , videos);

        return "selectReturnVideo";
    }

    @RequestMapping(value = "returnVideo")
    public String returnVideo(@RequestParam String videoId ,
                              @RequestParam String userId ,
                              @RequestParam String rentInfoId ,
                              Model model) {
        System.out.println(videoId + " , " + userId + " , "+ rentInfoId);
        //view 꾸미기용 map
        Map<String ,Object> map = new HashedMap();

        //Todo 각 서비스로 분할
        map = videoService.returnVideo(Integer.parseInt(videoId), Integer.parseInt(userId), Integer.parseInt(rentInfoId));

        model.addAttribute("userName" , map.get("userName"));
        model.addAttribute("fee" , map.get("fee"));

        return "payment";
    }




}
