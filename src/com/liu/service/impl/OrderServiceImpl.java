package com.liu.service.impl;

import com.liu.constant.BookStoreConstant;
import com.liu.dao.impl.OrderDaoImpl;
import com.liu.pojo.Cart;
import com.liu.pojo.Order;
import com.liu.pojo.User;
import com.liu.service.OrderService;
import com.liu.util.JDBCUtils;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {
    @Override
    public String checkout(Cart existCart, User existUser) {

        try {
            //①①①①①①①①①①①①①①①①①①①①①①①①①①①①①①①①①开启事务①①①①①①①①①①①①①①①①①①①①①①①①①①①①①①①①①
            JDBCUtils.startTransaction();
            //--------------------------------1.生成订单记录--------------------------------
            //添加记录并返回主键值
            OrderDaoImpl orderDao = new OrderDaoImpl();
            Order order = new Order();
            // 1.1,设置创建时间
            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            order.setCreateTime(createTime);
            // 1.2,设置订单序列号
            String orderSequence = UUID.randomUUID().toString().replace("-", "");
            order.setOrderSequence(orderSequence);
            // 1.3,设置订单状态
            order.setOrderStatus(BookStoreConstant.ORDER_UNCOMPLETE);
            // 1.4,设置总数量
            Integer totalCount = existCart.getTotalCount();
            order.setTotalCount(totalCount);
            // 1.5,设置总金额
            Double totalAmount = existCart.getTotalAmount();
            order.setTotalAmount(totalAmount);
            // 1.6,设置用户id
            Integer userId = existUser.getUserId();
            order.setUserId(userId);
            orderDao.insert(order);

        } catch (Exception e) {
            e.printStackTrace();
            //③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③回滚事务③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③③
            try {
                JDBCUtils.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                JDBCUtils.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return null;
    }
}
