package com.liu.dao;

import com.liu.dao.base.BaseDao;
import com.liu.pojo.User;

import java.util.List;

public interface UserDao extends BaseDao<User> {
    /**
     * 根据账户和密码查询用户记录
     * @param username
     * @param password
     * @return
     */
    User getByUsernameAndPassword(String username, String password);

    /**
     * 根据用户查询用户记录
     * @param username
     * @return
     */
    User getByUsername(String username);

}
