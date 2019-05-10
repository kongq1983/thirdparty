package com.kq.javassist.demo;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.Modifier;

/**
 * MakeClassDemo
 * 创建一个不存在的类
 * @author kq
 * @date 2019-05-10
 */
public class MakeClassDemo {

    public static void main(String[] args)  throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("com.kq.javassist.entity.DynamicBean");
        cc.setSuperclass(pool.get("com.kq.javassist.entity.BaseEntity"));

//        CtField f = new CtField(CtClass.intType, "age", cc);
//        f.setModifiers(Modifier.PUBLIC);
//        cc.addField(f);

        cc.writeFile("D:\\javassist\\");

    }

}
