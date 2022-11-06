package com.liu.controller;

import com.liu.constant.BookStoreConstant;
import com.liu.controller.base.ModelBaseServlet;
import com.liu.pojo.Cart;
import com.liu.pojo.User;
import com.liu.service.impl.OrderServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/protected/orderClient")
public class OrderClientController extends ModelBaseServlet {
    /**
     * 转发到checkout.html
     * @param request
     * @param response
     */
    public void toCheckoutPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processTemplate("cart/checkout", request, response);
    }

    public void checkout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OrderServiceImpl orderService = new OrderServiceImpl();
        // 订单结算
        Cart existCart = (Cart) request.getSession().getAttribute(BookStoreConstant.SESSION_KEY_CART);
        User existUser = (User) request.getSession().getAttribute(BookStoreConstant.SESSION_KEY_USER);
        String orderSequence = orderService.checkout(existCart, existUser);
        // 订单结算完成，清空购物车
        request.getSession().removeAttribute(BookStoreConstant.SESSION_KEY_CART);
        //跳转到checkout.html页面 , 并展示订单序列号(orderSequence)
        request.getSession().setAttribute(BookStoreConstant.SESSION_KEY_ORDER_SEQUENCE, orderSequence);
        response.sendRedirect(request.getContextPath() + "/protected/orderClient?method=toCheckoutPage");
    }

}
