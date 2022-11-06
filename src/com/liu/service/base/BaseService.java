package com.liu.service.base;

import com.liu.pojo.PageInfo;

import java.util.List;

public interface BaseService<T> {

    int insert(T user);

    int delete(Integer id);

    int update(T user);

    T getById(Integer id);

    List<T> findAll();

    PageInfo<T> findByPage(Long currentPage);
}
