package com.liu.service.impl;

import com.liu.dao.impl.UserDaoImpl;
import com.liu.pojo.PageInfo;
import com.liu.pojo.User;
import com.liu.service.UserService;
import com.liu.util.MD5Utils;

import java.util.List;

/**
 * 业务层
 * 实现业务层规范
 */
public class UserServiceImpl implements UserService {
    @Override
    public User login(String username, String password) {
        UserDaoImpl userDao = new UserDaoImpl();
        // 校验账户是否正确
        User dbUser = userDao.getByUsername(username);
        if (null == dbUser) {
            // 账户错误，登录失败
            return null;
        }
        // 账户正确，校验密码是否正确
        // password : 输入的明文
        // dbPassword : 数据库的密文
        String dbPassword = dbUser.getUserPwd();
        boolean verify = MD5Utils.verify(password, dbPassword);
        if (verify) {
            // 密码正确，登录陈宫
            return dbUser;
        } else {
            // 密码错误，登录失败
            return null;
        }
    }

    @Override
    public void regist(User user) {
        UserDaoImpl userDao = new UserDaoImpl();
        // 获取明文
        String passowrd = user.getUserPwd();
        // 获取盐值
        String salt = MD5Utils.generateSalt();
        // 获取密文
        String saltMd5Password = MD5Utils.generateMD5AndSalt(passowrd, salt);
        // 保存密文
        user.setUserPwd(saltMd5Password);
        userDao.insert(user);

    }

    @Override
    public User getByUsername(String userName) {
        UserDaoImpl userDao = new UserDaoImpl();
        return userDao.getByUsername(userName);
    }

    @Override
    public int insert(User user) {
        return 0;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public PageInfo<User> findByPage(Long currentPage) {
        return null;
    }
}
