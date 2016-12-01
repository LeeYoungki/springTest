package com.poinblack.videomanager.dao;

import com.poinblack.videomanager.model.RentInfo;
import com.poinblack.videomanager.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("RentInfo")
public class RentInfoDaoImpl extends AbstractDao implements RentInfoDao{

    public int insertRentInfo(RentInfo rentInfo) {
        return (int) getSession().save(rentInfo);
    }

    @SuppressWarnings("unchecked")
    public List<RentInfo> selectAllRentInfo() {
        Criteria criteria = getSession().createCriteria(RentInfo.class);
        return criteria.list();
    }

    public void deleteRentInfo(int id) {
        Query query = getSession().createQuery("delete RentInfo where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public RentInfo selectByRentInfo(int id) {
        Criteria criteria = getSession().createCriteria(RentInfo.class);
        criteria.add(Restrictions.eq("id", id));
        return (RentInfo) criteria.uniqueResult();
    }

    public void updateRentInfo(RentInfo rentInfo) {
        getSession().update(rentInfo);
    }

    public List<RentInfo> selectByUser(User user) {
        Criteria criteria = getSession().createCriteria(RentInfo.class);
        criteria.add(Restrictions.eq("user",user));
        return criteria.list();
    }
}
