package com.liu.dao.base;

import com.liu.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BaseDaoOperate<T> {

    /**
     * 批量操作
     * @param sql
     * @param paramArrays
     */
    public void batch(String sql, Object[][] paramArrays) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            System.out.println("connection : " + connection);
            System.out.println(Thread.currentThread().getName());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }

    }

    /**
     * 添加记录并返回主键值
     * @param sql
     * @param args
     * @return
     */
    public Integer insert(String sql, Object... args) {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            System.out.println("conenction : " + connection);
            System.out.println(Thread.currentThread().getName());
            Integer generatedKey = new QueryRunner().insert(
                    connection,
                    sql,
                    new ResultSetHandler<Integer>() {
                        @Override
                        public Integer handle(ResultSet rs) throws SQLException {
                            Integer generatedKey = null;
                            if (rs.next()) {
                                generatedKey = rs.getInt(1);
                            }
                            return generatedKey;
                        }
                    },
                    args
            );
            return generatedKey;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }

        return null;
    }

    /**
     * 增加，删除 ，修改
     * @param sql ： sql语句
     * @param args ： 输入参数
     * @return
     */
    public int update(String sql, Object... args) {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            return new QueryRunner()
                    .update(
                            connection,
                            sql,
                            args
                    );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null);
        }

        return -250;
    }

    /**
     * 查询单条记录
     *
     * @param clazz : 运行时对象 ， 规定返回结果的类型
     * @param sql
     * @param args
     * @return
     */
    public T findOne(Class<T> clazz, String sql, Object... args) {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            return new QueryRunner()
                    .query(
                            connection,
                            sql,
                            new BeanHandler<>(clazz),
                            args
                    );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection, null);
        }

        return null;
    }

    /**
     * 查询多条记录
     *
     * @param clazz ：运行时对象，规定了集合中的元素类型
     * @param sql ：sql语句
     * @param args ：输入参数
     * @return
     */
    public List<T> findList(Class<T> clazz, String sql, Object... args) {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            return new QueryRunner()
                    .query(
                            connection,
                            sql,
                            new BeanListHandler<>(clazz),
                            args
                    );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null);
        }

        return null;
    }

    /**
     * 查询记录个数
     *
     * @param sql
     * @return
     */
    public Long getTotalSize(String sql) {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();

            return new QueryRunner()
                    .query(
                            connection,
                            sql,
                            new ScalarHandler<>()
                    );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection, null);
        }

        return -250L;
    }



}
