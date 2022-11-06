package com.liu.service;


import com.liu.pojo.User;
import com.liu.service.base.BaseService;

/**
 * 定义业务层规范
 */
public interface UserService extends BaseService<User> {

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);

    /**
     * 注册
     *
     * @param user
     */
    void regist(User user);

    /**
     * 根据账户查询用户记录
     *
     * @param userName
     * @return
     */
    User getByUsername(String userName);
}
