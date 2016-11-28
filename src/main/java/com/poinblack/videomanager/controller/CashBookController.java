package com.poinblack.videomanager.controller;

import com.poinblack.videomanager.model.CashBook;
import com.poinblack.videomanager.service.CashBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class CashBookController {

    @Autowired
    private CashBookService cashBookService;

    @RequestMapping(value = "CashBookList")
    public String CashBookList(Model model) {
        List<CashBook> cashBooks = cashBookService.selectAllCashBook();
        model.addAttribute("cashBooks" , cashBooks);

        return "CashBookList";
    }
}
