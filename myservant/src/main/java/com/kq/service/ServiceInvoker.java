package com.kq.service;

import com.kq.myservice.IService;

import java.util.ServiceLoader;

/**
 * Created by qikong on 2019/3/23.
 */
public class ServiceInvoker {

    public static void main(String[] args) {
        ServiceLoader<IService> serviceLoader = ServiceLoader.load(IService.class);

        for(IService service : serviceLoader) {
            service.show();
        }
    }

}
