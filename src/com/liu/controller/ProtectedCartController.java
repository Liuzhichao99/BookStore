package com.liu.controller;

import com.liu.constant.BookStoreConstant;
import com.liu.controller.base.ModelBaseServlet;
import com.liu.pojo.Book;
import com.liu.pojo.Cart;
import com.liu.pojo.CartItem;
import com.liu.pojo.vo.ResultVO;
import com.liu.service.impl.BookServiceImpl;
import com.liu.util.JSONUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * "/protected/cart" ： 受保护，只有在登录状态才能访问
 */
@WebServlet("/protected/cart")
public class ProtectedCartController extends ModelBaseServlet {

    /**
     * 获取购物车信息
     *
     * @param request
     * @param response
     */
    public void getCart(HttpServletRequest request, HttpServletResponse response) {
        Cart existCart = (Cart) request.getSession().getAttribute(BookStoreConstant.SESSION_KEY_CART);
        ResultVO<Cart> resultVO = null;
        if (null == existCart) {
            // 获取购物车信息失败
            resultVO = ResultVO.fail("获取购物车信息失败", BookStoreConstant.GET_CART_FAIL, null);
        } else {
            ResultVO.success("获取购物车成功",BookStoreConstant.GET_CART_SUCCESS,existCart);
        }
        JSONUtils.javaBean2JsonStrAndResponse(response, resultVO);
    }

    /**
     * 添加图书到购物车
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
        request.getSession().setAttribute(BookStoreConstant.SESSION_KEY_CART, existCart);
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
        processTemplate("cart/cart", request, response);
    }

    /**
     * 清空购物车
     *
     * @param request
     * @param response
     */
    public void clearCart(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(BookStoreConstant.SESSION_KEY_CART);
        // 清空购物车成功，重定向toCartPage方法
        ResultVO resultVO = ResultVO.success("清空购物车成功!", BookStoreConstant.GET_CART_SUCCESS, null);
        JSONUtils.javaBean2JsonStrAndResponse(response, resultVO);
    }

    /**
     * 购物车项数量减一
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void cartItemCountDecrease(HttpServletRequest request, HttpServletResponse response) {
        Integer bookId = Integer.valueOf(request.getParameter("bookId"));

        Cart existCart = (Cart) request.getSession().getAttribute(BookStoreConstant.SESSION_KEY_CART);
        // 获取购物车当前数量
        CartItem cartItem = existCart.getCartItemMap().get(bookId);
        Integer count = cartItem.getCount();
        if (count <= 1) {
            // 如果当前数量为1，直接一吹购物车项
            existCart.removeCartItem(bookId);
        } else {
            existCart.cartItemCountDecrease(bookId);
        }
        // 购物车项数量减一成功，重定向
        ResultVO resultVO = ResultVO.success("指定购物车刷领减一成功!", null, null);
        JSONUtils.javaBean2JsonStrAndResponse(response, resultVO);
    }

    /**
     * 购物车项数量加一
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void cartItemCountIncrease(HttpServletRequest request, HttpServletResponse response) {
        Integer bookId = Integer.valueOf(request.getParameter("bookId"));
        Cart existCart = (Cart) request.getSession().getAttribute(BookStoreConstant.SESSION_KEY_CART);
        existCart.cartItemCountIncrease(bookId);
        // 购物车项数量减一成功，重定向
        ResultVO resultVO = ResultVO.success("指定购物车项数量加一成功!", null, null);
        JSONUtils.javaBean2JsonStrAndResponse(response, resultVO);
    }

    /**
     * 删除购物车项
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void removeCartItem(HttpServletRequest request, HttpServletResponse response) {
        Integer bookId = Integer.valueOf(request.getParameter("bookId"));
        Cart existCart = (Cart) request.getSession().getAttribute(BookStoreConstant.SESSION_KEY_CART);
        existCart.removeCartItem(bookId);
        // 删除成功，重定向
        JSONUtils.javaBean2JsonStrAndResponse(
                response,
                ResultVO.success("删除指定购物车成功！", null, null)
        );
    }

    /**
     * 修改购物车项数量
     *
     * @param request
     * @param response
     */
    public void updateCartItemCount(HttpServletRequest request, HttpServletResponse response) {
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
        JSONUtils.javaBean2JsonStrAndResponse(
                response,
                ResultVO.success("指定购物车项数量修改成功!", null, null)
        );
    }

}
