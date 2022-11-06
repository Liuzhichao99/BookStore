package com.liu.dao.impl;

import com.liu.dao.BookDao;
import com.liu.dao.base.BaseDaoOperate;
import com.liu.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDaoOperate<Book> implements BookDao {

    @Override
    public Long getTotalSize() {
        return getTotalSize("select count(*) from t_book");
    }

    @Override
    public int insert(Book book) {
        return update(
                "insert into t_book values(null,?,?,?,?,?,?)",
                book.getBookName(),
                book.getAuthor(),
                book.getPrice(),
                book.getSales(),
                book.getStock(),
                book.getImgPath()
        );
    }

    @Override
    public int delete(Integer bookId) {
        return update(
                "delete from t_book where book_id = ?",
                bookId
        );
    }

    @Override
    public int update(Book book) {
        return update(
                "update t_book set book_name = ?, author = ?, price = ?, sales = ?, stock = ?, imgPath = ?",
                book.getBookName(),
                book.getAuthor(),
                book.getPrice(),
                book.getSales(),
                book.getStock(),
                book.getImgPath()
        );
    }

    @Override
    public Book getById(Integer bookId) {
        return findOne(
                Book.class,
                "select book_id bookId, book_name bookName, author, price, sales, stock, imgPath where user_id = ?",
                bookId
        );
    }

    @Override
    public List<Book> findByPage(Long begin, Integer pageSize) {
        return findList(
                Book.class,
                "select book_id bookId, book_name bookName, author, price, sales, stock, img_path imgPath from t_book limit ?, ?",
                begin,pageSize
        );
    }

    @Override
    public List<Book> findAll() {
        return findList(
          Book.class,
          "select book_id bookId, book_name bookName, author, price, sales, stock, img_path imgPath"
        );
    }
}
