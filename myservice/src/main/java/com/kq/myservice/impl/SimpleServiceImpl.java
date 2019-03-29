package com.kq.myservice.impl;

import com.kq.myservice.IService;

/**
 * Created by qikong on 2019/3/23.
 */
public class SimpleServiceImpl implements IService{

    @Override
    public void show() {
        System.out.println("hi i come from the servcies loader ! ");
    }
}
