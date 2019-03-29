package com.kq.drools.entity;

import java.math.BigDecimal;

/**
 * SaleOrder
 *
 * @author kq
 * @date 2019-03-29
 */
public class SaleOrder {

    private String name;
    private Float price;

    /** 1: boss : 2:employee  3: other*/
    private Integer indentity;
    private Float discount;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getIndentity() {
        return indentity;
    }

    public void setIndentity(Integer indentity) {
        this.indentity = indentity;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SaleOrder{");
        sb.append("name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append(", indentity=").append(indentity);
        sb.append(", discount=").append(discount);
        sb.append('}');
        return sb.toString();
    }
}
