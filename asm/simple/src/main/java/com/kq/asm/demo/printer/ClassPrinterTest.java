package com.kq.asm.demo.printer;

import org.objectweb.asm.ClassReader;

/**
 * @author kq
 * @date 2022-07-18 16:50
 * @since 2020-0630
 */
public class ClassPrinterTest {

    public static void main(String[] args) throws Exception{
        ClassPrinter cp = new ClassPrinter();
//        ClassReader cr = new ClassReader("java.lang.Runnable");
//        ClassReader cr = new ClassReader("com.kq.asm.demo.helloworld.HelloWorldDemo");
        ClassReader cr = new ClassReader("com.kq.asm.demo.printer.ClassPrinterEntity");
        cr.accept(cp, 0);
    }

}
