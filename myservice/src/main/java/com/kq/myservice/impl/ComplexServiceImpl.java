package com.kq.myservice.impl;

import com.kq.myservice.IService;

/**
 * ComplexServiceImpl
 *
 * @author kq
 * @date 2019-11-30
 */
public class ComplexServiceImpl implements IService {

    @Override
    public void show() {
        System.out.println("hi i come from the servcies loader ! ComplexServiceImpl!");
    }
}
