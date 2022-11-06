package com.liu.service.impl;

import com.liu.dao.impl.BookDaoImpl;
import com.liu.pojo.Book;
import com.liu.pojo.PageInfo;
import com.liu.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public Long getTotalPage() {
        Integer pageSize = 3;
        BookDaoImpl bookDao = new BookDaoImpl();
        Long totalSize = bookDao.getTotalSize();
        Long totalPage = (totalSize % pageSize == 0) ? (totalSize / pageSize) : (totalSize / pageSize + 1);
        return totalPage;
    }

    @Override
    public int insert(Book book) {
        BookDaoImpl bookDao = new BookDaoImpl();
        book.setImgPath("static/uploads/jieyouzahuodian.jpg");
        return bookDao.insert(book);
    }

    @Override
    public int delete(Integer bookId) {
        BookDaoImpl bookDao = new BookDaoImpl();
        return bookDao.delete(bookId);
    }

    @Override
    public int update(Book book) {
        BookDaoImpl bookDao = new BookDaoImpl();
        return bookDao.update(book);
    }

    @Override
    public Book getById(Integer bookId) {
        BookDaoImpl bookDao = new BookDaoImpl();
        return bookDao.getById(bookId);
    }

    @Override
    public List<Book> findAll() {
        BookDaoImpl bookDao = new BookDaoImpl();
        return bookDao.findAll();
    }

    @Override
    public PageInfo<Book> findByPage(Long currentPage) {
        // 1.创建PageInfo对象
        PageInfo<Book> pageInfo = new PageInfo<>();
        // 2.设置当前页数
        pageInfo.setCurrentPage(currentPage);
        // 3.设置每页记录数
        Integer pageSize = 3;
        pageInfo.setPageSize(pageSize);
        // 4.设置总记录数
        BookDaoImpl bookDao = new BookDaoImpl();
        Long totalSize = bookDao.getTotalSize();
        pageInfo.setTotalSize(totalSize);
        // 5.设置总页数
        // 可以整数 ： totalPage = totalSize / pageSize
        //不可以整除 : totalPage = totalSize / pageSize + 1;
        long totalPage = (totalSize % pageSize == 0) ? (totalSize / pageSize) : (totalSize / pageSize + 1);
        pageInfo.setTotalPage(totalPage);
        // 6.设置当前页数据 : select * from t_book limit begin , pageSize
        Long begin = (currentPage - 1) * pageSize;
        List<Book> data = bookDao.findByPage(begin, pageSize);
        pageInfo.setData(data);
        return pageInfo;
    }
}
