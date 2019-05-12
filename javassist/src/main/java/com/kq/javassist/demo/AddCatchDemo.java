package com.kq.javassist.demo;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

/**
 * AddCatchDemo
 *
 * @author kq
 * @date 2019/5/12
 */
public class AddCatchDemo {

    public static void main(String[] args) throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("com.kq.javassist.service.impl.RegisterServiceImpl");

        CtMethod registerMethod = CtNewMethod.make("public void register(String username,String password) {};", cc);
        cc.addMethod(registerMethod);

        registerMethod.setBody("{ System.out.println($1); System.out.println($2); }");

        CtClass etype = ClassPool.getDefault().get("java.lang.Exception");
        registerMethod.addCatch("{ System.out.println($e); throw $e; }", etype);


        cc.writeFile("D:\\javassist\\");
    }

}
