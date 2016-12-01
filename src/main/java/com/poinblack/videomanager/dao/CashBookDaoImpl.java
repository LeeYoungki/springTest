package com.poinblack.videomanager.dao;

import com.poinblack.videomanager.model.CashBook;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CashBookDao")
public class CashBookDaoImpl extends AbstractDao implements CashBookDao{

    public void insertCashBook(CashBook cashBook) {
        persist(cashBook);
    }

    @SuppressWarnings("unchecked")
    public List<CashBook> selectAllCashBook() {
        Criteria criteria = getSession().createCriteria(CashBook.class);
        return criteria.list();
    }

    public void deleteCashBook(int id) {
        Query query = getSession().createQuery("delete CashBook where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public CashBook selectByCashBook(int id){
        Criteria criteria = getSession().createCriteria(CashBook.class);
        criteria.add(Restrictions.eq("id", id));
        return (CashBook) criteria.uniqueResult();
    }

    public CashBook selectByRentInfo(int rentId) {
        Criteria criteria = getSession().createCriteria(CashBook.class);
        criteria.add(Restrictions.eq("rentInfo.id", rentId));
        return (CashBook) criteria.uniqueResult();
    }

    public void updateCashBook(CashBook cashBook){
        getSession().update(cashBook);
    }
}
