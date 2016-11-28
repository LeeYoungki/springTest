package com.poinblack.videomanager.service;

import com.poinblack.videomanager.model.CashBook;

import java.util.List;

public interface CashBookService {
    void insertCashBook(CashBook cashBook);

    List<CashBook> selectAllCashBook();

    void deleteByCashBookId(int cb_id);

    CashBook selectByCashBookId(int cb_id);

    void updateCashBook(CashBook cashBook);
}
