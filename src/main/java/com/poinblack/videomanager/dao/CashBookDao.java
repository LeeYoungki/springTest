package com.poinblack.videomanager.dao;

import com.poinblack.videomanager.model.CashBook;
import com.poinblack.videomanager.model.CashBook;

import java.util.List;

public interface CashBookDao {

    void insertCashBook(CashBook cashBook);

    List<CashBook> selectAllCashBook();

    void deleteCashBook(String cb_id);

    CashBook selectByCashBook(String cb_id);

    void updateCashBook(CashBook cashBook);
}
