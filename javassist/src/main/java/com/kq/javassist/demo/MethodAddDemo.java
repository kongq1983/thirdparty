package com.kq.javassist.demo;

import javassist.*;

import java.lang.reflect.Modifier;

/**
 * MethodAddDemo
 *
 * @author kq
 * @date 2019-05-10
 */
public class MethodAddDemo {

    public static void main(String[] args) throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("com.kq.javassist.entity.DynamicBean");
        cc.setSuperclass(pool.get("com.kq.javassist.entity.BaseEntity"));

        //添加field字段
        CtField f = new CtField(CtClass.intType, "age", cc);
        //私有字段
        f.setModifiers(Modifier.PRIVATE);
//        f.setModifiers(Modifier.PUBLIC);
        cc.addField(f);

        // 动态创建get方法
        CtMethod getAgeMethod = CtNewMethod.make("public int getAge() { return age;};", cc);
        cc.addMethod(getAgeMethod);

        //动态创建set方法
        CtMethod setAgeMethod = CtNewMethod.make("public void setAge(int age) { this.age=age;};", cc);
        cc.addMethod(setAgeMethod);


        cc.writeFile("D:\\javassist\\");
    }

}
