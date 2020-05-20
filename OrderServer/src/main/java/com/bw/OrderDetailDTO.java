package com.bw;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailDTO {
    //商品id
    private long id;
    //金额字段
    private BigDecimal price;
    private int qty;
}
