package com.poinblack.videomanager.service;

import com.poinblack.videomanager.model.CashBook;

import java.util.List;

public interface CashBookService {
    void insertCashBook(CashBook cashBook);

    List<CashBook> selectAllCashBook();

    void deleteByCashBookId(String cb_id);

    CashBook selectByCashBookId(String cb_id);

    void updateCashBook(CashBook cashBook);
}
