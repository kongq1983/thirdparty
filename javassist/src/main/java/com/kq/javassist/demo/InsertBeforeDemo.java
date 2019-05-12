package com.kq.javassist.demo;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * InsertBeforeDemo
 *
 * @author kq
 * @date 2019/5/12
 */
public class InsertBeforeDemo {

    public static void main(String[] args) throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("com.kq.javassist.entity.Point");
        CtMethod m = cc.getDeclaredMethod("move");
        m.insertBefore("{ System.out.println($1); System.out.println($2); }");
        cc.writeFile("D:\\javassist\\");
    }

}
