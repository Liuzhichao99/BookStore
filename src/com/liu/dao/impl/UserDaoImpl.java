package com.liu.dao.impl;

import com.liu.dao.UserDao;
import com.liu.dao.base.BaseDaoOperate;
import com.liu.pojo.User;

import java.util.List;

public class UserDaoImpl extends BaseDaoOperate<User> implements UserDao {

    @Override
    public User getByUsernameAndPassword(String username, String password) {
        return findOne(
                User.class,
                "select user_id userId, user_name userName, user_pwd userPwd, email from t_user where user_name = ? and user_pwd = ?",
                username, password
        );
    }

    @Override
    public User getByUsername(String username) {
        return findOne(
                User.class,
                "select user_id userId, user_name userName, user_pwd userPwd, email from t_user where user_name = ?",
                username
        );
    }

    @Override
    public int insert(User user) {
        return update(
                "insert into t_user values(null, ?, ?, ?)",
                user.getUserName(),
                user.getUserPwd(),
                user.getEmail()
        );
    }

    @Override
    public int delete(Integer userId) {
        return update(
                "delete from t_user where user_id = ?",
                userId
        );
    }

    @Override
    public int update(User user) {
        return update(
                "update t_user set user_name = ?, user_pwd = ?, email = ?",
                user.getUserName(),
                user.getUserPwd(),
                user.getEmail(),
                user.getUserId()
        );
    }

    @Override
    public User getById(Integer userId) {
        return findOne(
                User.class,
                "select user_id userId, user_name userName, user_pwd userPwd, email from t_user where user_id = ?",
                userId
        );
    }

    @Override
    public List<User> findByPage(Long begin, Integer pageSize) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return findList(
                User.class,
                "select user_id userId, user_name userName, user_pwd userPwd, email from t_user"
        );
    }
}
