package com.liu.dao;

import com.liu.dao.base.BaseDao;
import com.liu.pojo.Book;

public interface BookDao extends BaseDao<Book> {

    /**
     * 获取总记录数
     * @return
     */
    Long getTotalSize();
}
