package com.kq.javassist.demo;

import com.kq.javassist.entity.Person;
import javassist.ClassPool;
import javassist.CtClass;

/**
 * SetSuperclassDemo
 *
 * @author kq
 * @date 2019-05-10
 */
public class SetSuperclassDemo {

    public static void main(String[] args) throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("com.kq.javassist.entity.Person");
        cc.setSuperclass(pool.get("com.kq.javassist.entity.BaseEntity"));
        cc.writeFile("D:\\javassist\\");

        Class<Person> personClass = cc.toClass();
        Person person = personClass.newInstance();
        person.setAge(18);
        System.out.println(person.getAge());

    }
}
