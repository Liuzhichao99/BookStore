package com.liu.controller;

import com.liu.controller.base.ViewBaseServlet;
import com.liu.pojo.Book;
import com.liu.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 转发到WEB-INF目录中的index.html
 */
@WebServlet("/index.html")
public class IndexController extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取图书列表
        BookServiceImpl bookService = new BookServiceImpl();
        List<Book> bookList = bookService.findAll();
        req.setAttribute("bookList", bookList);
        processTemplate("index", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}