package com.kq.javassist.entity;

/**
 * Company
 *
 * @author kq
 * @date 2019-05-09
 */
public class Company extends BaseEntity {

    private String name;
    private String address;



    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}