package com.kq.service.impl;

import com.kq.myservice.IService;

/**
 * MyServiceImpl
 *
 * @author kq
 * @date 2019-11-30
 */
public class MyServiceImpl implements IService {

    @Override
    public void show() {
        System.out.println("hi i come from the servcies loader ! MyServiceImpl!");
    }
}
