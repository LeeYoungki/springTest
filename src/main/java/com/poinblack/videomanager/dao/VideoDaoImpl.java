package com.poinblack.videomanager.dao;

import com.poinblack.videomanager.model.Video;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("VideoDao")
public class VideoDaoImpl extends AbstractDao implements VideoDao{

    public void insertVideo(Video video) {
        persist(video);
    }

    @SuppressWarnings("unchecked")
    public List<Video> selectAllVideo() {
        Criteria criteria = getSession().createCriteria(Video.class);
        return criteria.list();
    }

    @Override
    public List<Video> selectRentVideo(String state) {
        Criteria criteria = getSession().createCriteria(Video.class);
        criteria.add(Restrictions.eq("state" , state));
        return criteria.list();
    }

    public void deleteVideo(int video_id) {
        Query query = getSession().createQuery("delete Video where video_id = :video_id");
        query.setParameter("video_id", video_id);
        query.executeUpdate();
    }

    public Video selectByVideo(int video_id){
        Criteria criteria = getSession().createCriteria(Video.class);
        criteria.add(Restrictions.eq("video_id",video_id));
        return (Video) criteria.uniqueResult();
    }

    public void updateVideo(Video video){
        getSession().update(video);
    }
}
