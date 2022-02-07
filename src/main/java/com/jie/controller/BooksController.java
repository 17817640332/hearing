package com.jie.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jie.pojo.Books;
import com.jie.service.impl.BooksServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 书表 前端控制器
 * </p>
 *
 * @author jie
 * @since 2021-03-31
 */
@RestController
@RequestMapping("/books")
@Api("获取书")
public class BooksController {

    @Autowired
    private BooksServiceImpl booksService;

    @GetMapping("/getBookName")
    @ApiOperation("获取可选择的书名")
    public List<String> getBooksName(){
        List<Books> list = booksService.list();

        ArrayList<String> bookName = new ArrayList<>();
        for (Books books : list) {
            bookName.add(books.getBookName());
        }
        return bookName;
    }


    @GetMapping("/getUnit")
    @ApiOperation("获取可选择的单元")
    public List<String> getBooksUnit(){
        List<Books> list = booksService.list();

        ArrayList<String> bookUnit = new ArrayList<>();
        for (Books books : list) {
            bookUnit.add(books.getBookName());
        }
        return bookUnit;
    }

}

