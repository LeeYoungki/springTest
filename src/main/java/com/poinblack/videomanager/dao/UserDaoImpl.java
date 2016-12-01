package com.poinblack.videomanager.dao;

import com.poinblack.videomanager.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserDao")
public class UserDaoImpl extends AbstractDao implements UserDao{

    public void insertUser(User user) {
        persist(user);
    }

    @SuppressWarnings("unchecked")
    public List<User> selectAllUser() {
        Criteria criteria = getSession().createCriteria(User.class);
        return criteria.list();
    }

    public void deleteUser(int id) {
        Query query = getSession().createQuery("delete User where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public User selectByUser(int id){
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        return (User) criteria.uniqueResult();
    }

    public void updateUser(User user){
        getSession().update(user);
    }
}
