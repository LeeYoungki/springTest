package com.poinblack.videomanager.service;

import com.poinblack.videomanager.config.DataSourceConfig;
import com.poinblack.videomanager.config.MvcConfig;
import com.poinblack.videomanager.dao.CashBookDao;
import com.poinblack.videomanager.dao.RentInfoDao;
import com.poinblack.videomanager.dao.VideoDao;
import com.poinblack.videomanager.model.RentInfo;
import com.poinblack.videomanager.model.Video;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, MvcConfig.class})
@WebAppConfiguration
@Transactional
public class VideoTest {

}
