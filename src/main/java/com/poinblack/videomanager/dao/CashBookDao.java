package com.poinblack.videomanager.dao;

import com.poinblack.videomanager.model.CashBook;
import com.poinblack.videomanager.model.CashBook;

import java.util.List;

public interface CashBookDao {

    void insertCashBook(CashBook cashBook);

    List<CashBook> selectAllCashBook();

    CashBook selectByCashBook(int id);

    CashBook selectByRentInfo(int rentId);

    void deleteCashBook(int id);

    void updateCashBook(CashBook cashBook);
}
