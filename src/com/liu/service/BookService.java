package com.liu.service;

import com.liu.service.base.BaseService;
import com.liu.pojo.Book;

public interface BookService extends BaseService<Book> {

    /**
     * 查询总页数
     * @return
     */
    Long getTotalPage();
}
