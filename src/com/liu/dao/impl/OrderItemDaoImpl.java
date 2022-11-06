package com.liu.dao.impl;

import com.liu.dao.OrderItemDao;
import com.liu.dao.base.BaseDaoOperate;
import com.liu.pojo.OrderItem;

public class OrderItemDaoImpl extends BaseDaoOperate<OrderItem> implements OrderItemDao {
    //二维数组 : 由一维数组组成
    //一维数组 : 由元素组成
    //params[3][6] : n是一维数组的数量 , m是一维数组中元素的数量
    //n : 批量操作的记录的个数 ;
    //m : 操作的记录的字段的个数  .
    //批量添加3条记录, 每一条记录有6个字段需要赋值.
    @Override
    public void insert(Object[][] paramsArray) {
        batch(
                "insert into t_order_item values(null,?,?,?,?,?,?)",
                paramsArray
        );

    }
}
