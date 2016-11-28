package com.poinblack.videomanager.dao;

import com.poinblack.videomanager.config.DataSourceConfig;
import com.poinblack.videomanager.config.MvcConfig;
import com.poinblack.videomanager.model.Video;
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
public class TestVideoDao {

    @Resource
    private VideoDao dao ;

    @Test
    public void selectTest() {
        System.out.println("Video selectTest : Start");

        System.out.println("Video selectTest : Start Connect");
        List<Video> list = dao.selectAllVideo();

        for (Video l : list) {
            System.out.println("Video : " + l.toString());
        }
    }

    @Test
    public void deleteTest(){
        System.out.println("Video deleteTest : Start");

        System.out.println("Video deleteTest : Start Connect");
        List<Video> list = dao.selectAllVideo();

        for (Video l : list) {
            dao.deleteVideo(l.getVideo_id());
        }
    }
}
