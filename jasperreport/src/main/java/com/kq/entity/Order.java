package com.kq.entity;

import java.math.BigDecimal;

/**
 * Order
 *
 * @author kq
 * @date 2019/5/13
 */
public class Order {

    private String name;
    private Integer amount;
    private BigDecimal price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
