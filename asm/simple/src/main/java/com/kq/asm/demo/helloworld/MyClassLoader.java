package com.kq.asm.demo.helloworld;

import com.kq.asm.demo.helloworld.HelloWorldDemo;

/**
 * @author kq
 * @date 2022-07-18 15:15
 * @since 2020-0630
 */
public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if ("sample.HelloWorld".equals(name)) {
            byte[] bytes = HelloWorldDemo.dump();
            Class<?> clazz = defineClass(name, bytes, 0, bytes.length);
            return clazz;
        }

        throw new ClassNotFoundException("Class Not Found: " + name);
    }
}
