package com.bw.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="my_order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price = new BigDecimal(0);
    private int qty;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;

    public OrderDetail(BigDecimal price, int qty, Order order, Product product) {
        this.price = price;
        this.qty = qty;
        this.order=order;
        this.product = product;
    }
}
