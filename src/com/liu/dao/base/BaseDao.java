package com.liu.dao.base;

import java.util.List;

public interface BaseDao<T> {

    /**
     * 添加
     * @param book
     * @return
     */
    int insert(T book);


    /**
     * 删除
     * @param bookId
     * @return
     */
    int delete(Integer bookId);

    /**
     * 修改
     * @param book
     * @return
     */
    int update(T book);

    /**
     * 根据id查询信息
     * @param bookId
     * @return
     */
    T getById(Integer bookId);


    /**
     * 分页查询
     * @param begin
     * @param pageSize
     * @return
     */
    List<T> findByPage(Long begin, Integer pageSize);


    /**
     * 查询所有
     * @return
     */
    List<T> findAll();

}
