package com.liu.constant;

public class BookStoreConstant {
    public static final String SESSION_KEY_USER = "existUser";
    public static final String SESSION_KEY_CODE = "existCode";
    public static final String SESSION_KEY_CART = "existCart";

    public static final Integer ORDER_UNCOMPLETE = 0 ;//订单未完成
    public static final Integer ORDER_COMPLETED = 1 ;//订单已完成


    public static final String SESSION_KEY_ORDER_SEQUENCE = "existOrderSequence";
    public static final Integer LOGIN_SUCCESS = 200;//登录成功
    public static final Integer LOGIN_FAIL_USERNAME = 201;//账户错误
    public static final Integer LOGIN_FAIL_PASSWORD = 202;//密码错误
    public static final Integer LOGOUT_SUCCESS = 300; // 注销成功
    public static final Integer LOGOUT_FAIL = 301;//注销失败
    public static final Integer FIND_ALLBOOK_SUCCESS = 400;//获取所有图书成功
    public static final Integer FIND_ALLBOOK_FAIL = 401;//获取所有图书失败
    public static final Integer GET_CART_FAIL = 501;//获取购物车信息失败
    public static final Integer GET_CART_SUCCESS = 500;//获取购物车信息成功
    public static final Integer ADD_BOOK_2_CART_SUCCESS = 600;//加入购物车成功
    public static final Integer CLEAR_CART_SUCCESS = 700;//清空购物车成功
}
