package com.poinblack.videomanager.dao;

import com.poinblack.videomanager.config.DataSourceConfig;
import com.poinblack.videomanager.config.MvcConfig;
import com.poinblack.videomanager.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, MvcConfig.class})
@WebAppConfiguration
@Transactional
public class TestUserDao {

    @Resource
    private UserDao dao ;

    @Test
    public void selectTest() {
        System.out.println("User selectTest : Start");

        System.out.println("User selectTest : Start Connect");
        List<User> list = dao.selectAllUser();

        for (User l : list) {
            System.out.println("user : " + l.toString());
        }
    }

    @Test
    public void deleteTest(){
        System.out.println("User deleteTest : Start");

        System.out.println("User deleteTest : Start Connect");
        List<User> list = dao.selectAllUser();

        for (User l : list) {
            dao.deleteUser(l.getUser_id());
        }
    }
}
