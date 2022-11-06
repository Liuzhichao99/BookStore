package com.liu.service;

import com.liu.pojo.Cart;
import com.liu.pojo.User;

public interface OrderService {
    /**
     * 订单结算
     * @param existCart
     * @param existUser
     * @return : 订单序列号
     */
    String checkout(Cart existCart, User existUser);
}
