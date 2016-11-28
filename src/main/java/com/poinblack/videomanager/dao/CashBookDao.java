package com.poinblack.videomanager.dao;

import com.poinblack.videomanager.model.CashBook;
import com.poinblack.videomanager.model.CashBook;

import java.util.List;

public interface CashBookDao {

    void insertCashBook(CashBook cashBook);

    List<CashBook> selectAllCashBook();

    CashBook selectByCashBook(int cb_id);

    CashBook selectByRentInfo(int cb_rent_id);

    void deleteCashBook(int cb_id);

    void updateCashBook(CashBook cashBook);
}
