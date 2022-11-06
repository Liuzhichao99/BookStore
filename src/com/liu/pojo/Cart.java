package com.liu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Integer totalCount; // 商品总数量
    private Double totalAmount; // 商品总金额
    // 购物车列表 ， key=bookId, value=CartItem
    private Map<Integer, CartItem> cartItemMap = new LinkedHashMap<>();

    /**
     * 指定购物车项数量减1
     *
     * @param bookId : 指定购物车项的bookId
     */
    public void cartItemCountDecrease(Integer bookId) {
        CartItem cartItem = cartItemMap.get(bookId);
        cartItem.countDecrease();
    }

    /**
     * 指定购物车项数量加1
     *
     * @param bookId
     */
    public void cartItemCountIncrease(Integer bookId) {
        CartItem cartItem = cartItemMap.get(bookId);
        cartItem.countIncrease();;
    }

    /**
     * 修改指定购物车项的数量
     *
     * @param bookId
     * @param newCount
     */
    public void updateCartItemCount(Integer bookId, Integer newCount) {
        CartItem cartItem = cartItemMap.get(bookId);
        cartItem.setCount(newCount);
    }

    /**
     * 移除指定购物车项
     *
     * @param bookId
     */
    public void removeCartItem(Integer bookId) {
        cartItemMap.remove(bookId);
    }

    /**
     * 获取商品总数量
     *
     * @return
     */
    public Integer getTotalCount() {
        Collection<CartItem> cartItemList = cartItemMap.values();

        Integer totalCount = 0;
        for (CartItem cartItem : cartItemList) {
            Integer count = cartItem.getCount();
            totalCount += count;
        }
        this.totalCount = totalCount;
        return this.totalCount;
    }

    /**
     * 获取商品总金额
     *
     * @return
     */
    public Double getTotalAmount() {
        Collection<CartItem> cartItemList = cartItemMap.values();
        BigDecimal totalAmount = new BigDecimal(0.0 + "");
        for (CartItem cartItem : cartItemList) {
            // 获取购物车项小计
            BigDecimal amount = new BigDecimal(cartItem.getAmount() + "");
            totalAmount = totalAmount.add(amount);
        }

        this.totalAmount = totalAmount.doubleValue();
        return this.totalAmount;
    }

    /**
     * 将指定图书添加到购物车中
     *
     * @param book : 指定图书
     */
    public void addBook2Cart(Book book) {
        Integer bookId = book.getBookId();
        if (cartItemMap.containsKey(bookId)) {
            // 如果不是第一次添加，数量=原有数量 + 1
            cartItemCountIncrease(bookId);
        } else {
            // 如果是第一次添加，创建购物车项，数量=1， 并添加到购物车
            CartItem cartItem = new CartItem(
                    bookId,
                    book.getImgPath(),
                    book.getBookName(),
                    1,
                    book.getPrice(),
                    1 * book.getPrice()
            );
            cartItemMap.put(bookId, cartItem);
        }
    }

    /**
     * 清空购物车
     */
    public void clear() {
        this.totalAmount = 0.0;
        this.totalCount = 0;
        this.cartItemMap.clear();
    }

}
