package com.liu.dao.impl;

import com.liu.dao.OrderDao;
import com.liu.dao.base.BaseDaoOperate;
import com.liu.pojo.Order;

public class OrderDaoImpl extends BaseDaoOperate<Order> implements OrderDao {
    @Override
    public void insert(Order order) {
        String sql = "insert into t_order values(null,?,?,?,?,?,?)";
        Integer generatedKey = insert(sql,order.getOrderSequence(),
                order.getCreateTime(),
                order.getTotalCount(),
                order.getTotalAmount(),
                order.getOrderStatus(),
                order.getUserId());
        order.setOrderId(generatedKey);

    }
}
