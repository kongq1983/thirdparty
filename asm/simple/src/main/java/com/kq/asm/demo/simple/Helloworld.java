package com.kq.asm.demo.simple;

/**
 * @author kq
 * @date 2022-07-18 15:28
 * @since 2020-0630
 */
import java.io.FileOutputStream;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Helloworld extends ClassLoader implements Opcodes {

    public static void main(final String args[]) throws Exception {


        //定义一个叫做Example的类
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_1, ACC_PUBLIC, "Example", null, "java/lang/Object", null);

        //生成默认的构造方法
        MethodVisitor mw = cw.visitMethod(ACC_PUBLIC,
                "<init>",
                "()V",
                null,
                null);

        //生成构造方法的字节码指令
        mw.visitVarInsn(ALOAD, 0);
        mw.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
        mw.visitInsn(RETURN);
        mw.visitMaxs(1, 1);
        mw.visitEnd();

        //生成main方法
        mw = cw.visitMethod(ACC_PUBLIC + ACC_STATIC,
                "main",
                "([Ljava/lang/String;)V",
                null,
                null);

        //生成main方法中的字节码指令
        mw.visitFieldInsn(GETSTATIC,
                "java/lang/System",
                "out",
                "Ljava/io/PrintStream;");

        mw.visitLdcInsn("Hello world!");
        mw.visitMethodInsn(INVOKEVIRTUAL,
                "java/io/PrintStream",
                "println",
                "(Ljava/lang/String;)V");
        mw.visitInsn(RETURN);
        mw.visitMaxs(2, 2);

        //字节码生成完成
        mw.visitEnd();

        // 获取生成的class文件对应的二进制流
        byte[] code = cw.toByteArray();


        //将二进制流写到本地磁盘上
        FileOutputStream fos = new FileOutputStream("D:\\log\\Example.class");
        fos.write(code);

        fos.close();

        //直接将二进制流加载到内存中
        Helloworld loader = new Helloworld();
        Class<?> exampleClass = loader.defineClass("Example", code, 0, code.length);

        //通过反射调用main方法
        exampleClass.getMethods()[0].invoke(null, new Object[] { null });

//        ClassWriter类是ASM中的核心API ， 用于生成一个类的字节码。 ClassWriter的visit方法定义一个类。
//
//        第一个参数V1_1是生成的class的版本号， 对应class文件中的主版本号和次版本号， 即minor_version和major_version 。
//
//        第二个参数ACC_PUBLIC表示该类的访问标识。这是一个public的类。 对应class文件中的access_flags 。
//
//        第三个参数是生成的类的类名。 需要注意，这里是类的全限定名。 如果生成的class带有包名， 如com.jg.zhang.Example， 那么这里传入的参数必须是com/jg/zhang/Example  。对应class文件中的this_class  。
//
//        第四个参数是和泛型相关的， 这里我们不关新， 传入null表示这不是一个泛型类。这个参数对应class文件中的Signature属性（attribute） 。
//
//        第五个参数是当前类的父类的全限定名。 该类直接继承Object。 这个参数对应class文件中的super_class 。
//
//        第六个参数是String[]类型的， 传入当前要生成的类的直接实现的接口。 这里这个类没实现任何接口， 所以传入null 。 这个参数对应class文件中的interfaces 。
//————————————————
//        版权声明：本文为CSDN博主「昨夜星辰_zhangjg」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。

//        原文链接：https://blog.csdn.net/zhangjg_blog/article/details/22976929


    }

}

//
//public class Example {
//    public Example() {
//    }
//
//    public static void main(String[] var0) {
//        System.out.println("Hello world!");
//    }
//}

