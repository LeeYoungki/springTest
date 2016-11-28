package com.poinblack.videomanager.service;

import com.poinblack.videomanager.dao.CashBookDao;
import com.poinblack.videomanager.model.CashBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CashBookService")
@Transactional
public class CashBookServiceImpl implements CashBookService {

    @Autowired
    CashBookDao dao;

    public void insertCashBook(CashBook cashBook) {
        dao.insertCashBook(cashBook);
    }

    public List<CashBook> selectAllCashBook() {
        return dao.selectAllCashBook();
    }

    public void deleteByCashBookId(int cb_id) {
        dao.deleteCashBook(cb_id);
    }

    public CashBook selectByCashBookId(int cb_id) {
        return dao.selectByCashBook(cb_id);
    }

    public void updateCashBook(CashBook cashBook) {
        dao.updateCashBook(cashBook);
    }
}
