package com.liu.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 结合Druid
 */
public class JDBCUtils {

    private static DataSource dataSource;

    /**
     * 开启事务
     * @throws SQLException
     */
    public static void startTransaction() throws SQLException {
        getConnection().setAutoCommit(false);
    }

    /**
     * 提交事务
     * @throws SQLException
     */
    public static void commit() throws SQLException {
        getConnection().commit();
    }

    /**
     * 关闭链接
     * @throws SQLException
     */
    public static void rollback() throws SQLException {
        getConnection().rollback();
    }

    public static void closeConnection() throws SQLException {
        Connection connection = getConnection();
        if (null != connection) {
            connection.close();
            connection = null;
            // 将ThreadLocal中的Connection对象移除，解决closed=false状态重置问题.
            threadLocal.remove();
        }
    }

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        Properties properties = new Properties();
        try {
            properties.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void release(Connection connection, Statement statement, ResultSet resultSet) {
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection = null;
        }

        if (null != statement) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement = null;
        }

        if (null != resultSet) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet = null;
        }
    }

    public static void release(Connection connection, Statement statement) {
        release(connection, statement, null);
    }

}
