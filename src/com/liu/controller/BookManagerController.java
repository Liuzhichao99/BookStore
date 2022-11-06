package com.liu.controller;

import com.liu.controller.base.ModelBaseServlet;
import com.liu.pojo.Book;
import com.liu.pojo.PageInfo;
import com.liu.service.impl.BookServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

@WebServlet("/bookManager")
public class BookManagerController extends ModelBaseServlet {

    /**
     * 分页查询，转发到book_manager.html
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void toBookManagerPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BookServiceImpl bookService = new BookServiceImpl();

        String currentPageStr = request.getParameter("currentPage");
        Long currentPage;
        if (null == currentPageStr) {
            //如果没有传递currentPage参数, 当前页数默认为1
            currentPage = 1L;
        } else {
            currentPage = Long.valueOf(currentPageStr);
            //currentPage范围应该在[1,totalPage]
            if (currentPage < 1) {
                // 如果小于1，就等于1
                currentPage = 1L;
            }
            Long totalPage = bookService.getTotalPage();
            if (currentPage > totalPage) {
                //如果大于最大页数 , 就等于最大页数
                currentPage = totalPage;
            }
        }
        // 获取分页数据
        PageInfo<Book> pageInfo = bookService.findByPage(currentPage);
        request.setAttribute("pageInfo", pageInfo);
        processTemplate("manager/book_manager", request, response);
    }

    /**
     * 转发到book_add.html
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void toBookAddPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processTemplate("manager/book_add", request, response);
    }

    public void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Book book = new Book();

        try {
            // 获取页面输入图书信息
            BeanUtils.populate(book, request.getParameterMap());
            // 调用业务层代码
            BookServiceImpl bookService = new BookServiceImpl();
            int insert = bookService.insert(book);
            if (insert > 0) {
                // 添加图书成功 ，重定向（分页查询，展示图书列表）
                response.sendRedirect(request.getContextPath() + "/bookManager?method=toBookManagerPage");
            } else {
                // 数据回显，显示之前输入的信息
                request.setAttribute("book", book);
                // 添加图书失败，转发回book_add.html页面
                toBookAddPage(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // 数据回显，显示之前输入的信息
            request.setAttribute("book", book);
            // 添加图书失败
            toBookAddPage(request, response);
        }
    }

    /**
     * 删除图书
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取图书id
        try {
            Integer bookId = Integer.valueOf(request.getParameter("bookId"));
            // 根据图书id获取图书所有信息
            BookServiceImpl bookService = new BookServiceImpl();
            int delete = bookService.delete(bookId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        //分页查询， 并展示图书列表
        response.sendRedirect(request.getContextPath() + "bookManager?method=toBookManagerPage");
    }

    /**
     * 根据id获取图书信息，转发到book_edit.html
     * @param request
     * @param response
     * @throws IOException
     */
    public void toBookEditPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取图书id
        Integer bookId = Integer.valueOf(request.getParameter("bookId"));
        // 根据图书id获取图书所有信息
        BookServiceImpl bookService = new BookServiceImpl();
        Book book = bookService.getById(bookId);
        // 将book对象存储到请求域对象
        request.setAttribute("book", book);

        processTemplate("manager/book_edit", request, response);
    }

    /**
     * 修改图书
     *
     * @param request
     * @param response
     */
    public void update(HttpServletRequest request, HttpServletResponse response) {
        Book book = new Book();
        try {
            // 获取页面输入的信息
            BeanUtils.populate(book, request.getParameterMap());
            // 完成修改图书业务
            BookServiceImpl bookService = new BookServiceImpl();
            int update = bookService.update(book);
            if (update > 0) {
                // 修改成功
                response.sendRedirect(request.getContextPath() + "/bookManager?method=toBookManagerPage");
            } else {
                // 修改失败
                // toBookEditPage();
                // 将之前输入的信息存储到请求域
                request.setAttribute("book", book);
                // 转发回book_edit.html
                processTemplate("manager/book_edit", request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
