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

    public void deleteCashBook(String cb_id) {
        Query query = getSession().createQuery("delete CashBook where cb_id = :cb_id");
        query.setParameter("cb_id", cb_id);
        query.executeUpdate();
    }

    public CashBook selectByCashBook(String cb_id){
        Criteria criteria = getSession().createCriteria(CashBook.class);
        criteria.add(Restrictions.eq("cb_id",cb_id));
        return (CashBook) criteria.uniqueResult();
    }

    public void updateCashBook(CashBook cashBook){
        getSession().update(cashBook);
    }
}
