package com.kq.javassist.write;

import javassist.*;

import java.io.IOException;

/**
 * CompanyWriteDemo
 *
 * @author kq
 * @date 2019-05-09
 */
public class CompanyWriteDemo {

    public static void main(String[] args) throws CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("com.kq.javassist.entity.Location");
        CtField f = new CtField(CtClass.intType, "age", cc);
        f.setModifiers(Modifier.PUBLIC);
        cc.addField(f);

        CtMethod m = CtNewMethod.make("public int setAge(int i) { return $1;};", cc);
        cc.addMethod(m);
//            m.setBody("{ return $1; }");

        CtMethod m1 = CtNewMethod.make("public int setNum(int i) { return $1;};", cc);
        cc.addMethod(m1);
        m1.insertBefore("{ System.out.println(\"Hello.say():\"); }");

//            m1.setBody("{System.out.println($1); return $1;}");

        cc.writeFile("D:\\javassist");
    }

}
