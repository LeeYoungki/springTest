package com.poinblack.videomanager.dao;

import com.poinblack.videomanager.config.DataSourceConfig;
import com.poinblack.videomanager.config.MvcConfig;
import com.poinblack.videomanager.model.Video;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, MvcConfig.class})
@WebAppConfiguration
@Transactional
public class TestRentInfoDao {

    @Resource
    private RentInfoDao dao ;

    @Before
    public void insertVideo(){
    }

    @Test
    public void selectTest() {
    }
}
