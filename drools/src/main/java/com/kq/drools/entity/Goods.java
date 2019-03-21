package com.kq.drools.entity;

/**
 * 
 */
public class Goods {

    private int discount = 100;

    private int type;

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "discount=" + discount +
                ", type=" + type +
                '}';
    }
}
