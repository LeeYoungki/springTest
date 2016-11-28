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
    public String videoList(@RequestParam String user_id,Model model) {
        List<Video> videos = videoService.selectAllVideo();
        model.addAttribute("videos" , videos);
        model.addAttribute("user_id" , user_id);

        return "videoList";
    }

    @RequestMapping(value = "rentVideo")
    public String rentVideo(@RequestParam String video_id ,
                            @RequestParam String user_id ,
                            Model model) {

        //view 꾸미기용 map
        Map<String ,Object> map = new HashedMap();
        map = videoService.rentVideo(Integer.parseInt(video_id), user_id);

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
    public String returnVideo(@RequestParam String video_id ,
                              @RequestParam String user_id ,
                              @RequestParam String rentInfo_id ,
                              Model model) {
        System.out.println(video_id + " , " + user_id + " , "+ rentInfo_id);
        //view 꾸미기용 map
        Map<String ,Object> map = new HashedMap();

        map = videoService.returnVideo(Integer.parseInt(video_id), user_id , Integer.parseInt(rentInfo_id));

        model.addAttribute("userName" , map.get("userName"));
        model.addAttribute("fee" , map.get("fee"));

        return "payment";
    }




}
