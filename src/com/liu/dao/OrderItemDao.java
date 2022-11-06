package com.liu.dao;

public interface OrderItemDao {
    /**
     * 批量添加
     * @param paramsArray[n][m] : 二维数组，n是批量操作的记录数 , m是每一次操作的字段数
     */
    void insert(Object[][] paramsArray);
}
