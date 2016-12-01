package com.poinblack.videomanager.controller;

import com.poinblack.videomanager.model.User;
import com.poinblack.videomanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "main")
    public String list() {
        return "main";
    }

    @RequestMapping(value = "insertUser")
    public String insertUser() {
        return "insertUser";
    }

    @RequestMapping(value = "join")
    public String join(@RequestParam Map<String , Object> map) {
        User user = new User();
        user.setUserId((String) map.get("userId"));
        user.setName((String) map.get("name"));
        user.setPhone((String) map.get("phone"));

        userService.insertUser(user);

        return "main";
    }

    @RequestMapping(value = "userList")
    public String userList(Model model) {
        List<User> users = userService.selectAllUser();
        model.addAttribute("users" , users);
        return "userList";
    }

    @RequestMapping(value = "selectRentUser")
    public String selectRentUser(Model model) {
        List<User> users = userService.selectAllUser();
        model.addAttribute("users" , users);
        return "selectRentUser";
    }

    //Todo hibernate 활용방법 구현 시 변경
    @RequestMapping(value = "userLateFeeList")
    public String userLateFeeList(Model model) {
        List<User> users = userService.selectAllUser();
        model.addAttribute("users" , users);
        return "userLateFeeList";
    }

    //Todo hibernate 활용방법 구현 시 변경
    @RequestMapping(value = "userCashBookList")
    public String userCashBookList(Model model) {
        List<User> users = userService.selectAllSortingUser();
        model.addAttribute("users" , users);
        return "userCashBookList";
    }
}
