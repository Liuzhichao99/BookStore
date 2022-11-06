package com.liu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Integer bookId; // 图书编号
    private String imgPath; // 图书图片
    private String bookName; // 图书名称
    private Integer count; // 商品数量
    private Double price; // 图书单价
    private Double amount; // 商品小计

    public void countDecrease() {this.count--;}

    public void countIncrease() {this.count++;}

    public Double getAmount() {
        BigDecimal countBigDecimal = new BigDecimal(this.count + "");
        BigDecimal priceBigDecimal = new BigDecimal(this.price + "");
        BigDecimal amountBigDecimal = countBigDecimal.multiply(priceBigDecimal);
        this.amount = amountBigDecimal.doubleValue();
        return this.amount;
    }
}
