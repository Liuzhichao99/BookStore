package com.liu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Integer bookId;//图书编号
    private String bookName;//图书名称
    private String author;//作者
    private Double price;//价格
    private Integer sales;//销量
    private Integer stock;//库存
    private String imgPath ;//图书封面
}
