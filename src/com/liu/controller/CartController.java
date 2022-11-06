package com.liu.controller;

import com.liu.constant.BookStoreConstant;
import com.liu.controller.base.ModelBaseServlet;
import com.liu.pojo.Book;
import com.liu.pojo.Cart;
import com.liu.pojo.CartItem;
import com.liu.service.impl.BookServiceImpl;
import org.omg.CORBA.INTERNAL;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;
import java.io.IOException;
import java.util.Properties;

/**
 * "/protected/cart" : 受保护 , 只有在登录状态才能访问
 * "/cart" : 不受保护 , 任意状态都能访问
 */
@WebServlet("/cart")
public class CartController extends ModelBaseServlet {
    /**
     * 添加图书到购物车
     *
     * @param request
     * @param response
     */
    public void addBook2Cart(HttpServletRequest request, HttpServletResponse response) {
        // 获取图书编号
        Integer bookId = Integer.valueOf(request.getParameter("bookId"));
        // 获取图书信息
        BookServiceImpl bookService = new BookServiceImpl();
        Book book = bookService.getById(bookId);
        // 获取购物车Cart对象
        Cart existCart = (Cart) request.getSession().getAttribute(BookStoreConstant.SESSION_KEY_CART);
        if (null == existCart) {
            // 第一次购物，创建新的Cart对象
            existCart = new Cart();
        }
        existCart.addBook2Cart(book);
        request.getSession().setAttribute(BookStoreConstant.SESSION_KEY_CART,existCart);
        // 添加图书到购物车成功，重定向首页

        try {
            response.sendRedirect(request.getContextPath() + "/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转发到cart.html
     *
     * @param request
     * @param response
     */
    public void toCartPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processTemplate("cart/cart",request,response);
    }

    /**
     * 清空购物车
     *
     * @param request
     * @param response
     */
    public void clearCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute(BookStoreConstant.SESSION_KEY_CART);
        // 清空购物车成功，重定向toCartPage方法
        response.sendRedirect(request.getContextPath() + "/protected/cart?method=toCartPage");
    }

    /**
     * 购物车项数量减一
     * @param request
     * @param response
     */
    public void cartItemCountDecrease(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer bookId = Integer.valueOf(request.getParameter("bookId"));

        Cart existCart =  (Cart) request.getSession().getAttribute(BookStoreConstant.SESSION_KEY_CART);
        // 获取购物车项当前数量
        CartItem cartItem = existCart.getCartItemMap().get(bookId);
        Integer count = cartItem.getCount();
        if (count <= 1) {
            //如果当前数量为1， 直接移除购物车项
            existCart.removeCartItem(bookId);
        } else {
            existCart.cartItemCountDecrease(bookId);
        }
        // 购物车项数量减一成功，重定向
        response.sendRedirect(request.getContextPath() + "/protected/cart?method=toCartPage");
    }

    /**
     * 购物车项数量加一
     * @param request
     * @param response
     */
    public void cartItemCountIncrease(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer bookId = Integer.valueOf(request.getParameter("bookId"));

        Cart existCart = (Cart) request.getSession().getAttribute(BookStoreConstant.SESSION_KEY_CART);
        // 获取购物车项当前数量
        existCart.cartItemCountIncrease(bookId);
        // 购物车数量加一成功重定向
        response.sendRedirect(request.getContextPath() + "/protected/cart?method=toCartPage");
    }

    /**
     * 删除购物车项
     * @param request
     * @param response
     */
    public void removeCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer bookId = Integer.valueOf(request.getParameter("bookId"));
        Cart existCart = (Cart) request.getSession().getAttribute(BookStoreConstant.SESSION_KEY_CART);
        existCart.removeCartItem(bookId);
        // 删除成功，重定向
        response.sendRedirect(request.getContextPath() + "/protected/cart?method=toCartPage");
    }

    /**
     * 修改购物车项数量
     * @param request
     * @param response
     */
    public void updateCartItemCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer bookId = Integer.valueOf(request.getParameter("bookId"));
        Integer newCount = Integer.valueOf(request.getParameter("newCount"));
        Cart existCart = (Cart) request.getSession().getAttribute(BookStoreConstant.SESSION_KEY_CART);

        if (newCount <= 0) {
            // 删除购物车项
            existCart.removeCartItem(bookId);
        } else {
            existCart.updateCartItemCount(bookId, newCount);
        }
        // 修改成功，重定向
        response.sendRedirect(request.getContextPath() + "/protected/cart?method=toCartPage");
    }

}
