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

    public void deleteUser(String user_id) {
        Query query = getSession().createQuery("delete User where user_id = :user_id");
        query.setParameter("user_id", user_id);
        query.executeUpdate();
    }

    public User selectByUser(String user_id){
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("user_id",user_id));
        return (User) criteria.uniqueResult();
    }

    public void updateUser(User user){
        getSession().update(user);
    }
}
