package com.kq.asm.demo.generate;

import com.kq.asm.util.WriteUtil;
import org.objectweb.asm.ClassWriter;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.V1_8;

/**
 * @author kq
 * @date 2022-07-18 17:01
 * @since 2020-0630
 */
public class GenerateInterface {

//    package pkg;
//    public interface Comparable extends Mesurable {
//        int LESS = -1;
//        int EQUAL = 0;
//        int GREATER = 1;
//        int compareTo(Object o);
//    }

    public static void main(String[] args) throws Exception{


        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_8, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                "pkg/Comparable", null, "java/lang/Object",
                new String[] { "pkg/Mesurable" });  // new String[] { "pkg/Mesurable" } 是接口
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I",   // int LESS = -1
                null, new Integer(-1)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", //  int EQUAL = 0
                null, new Integer(0)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I",  // int GREATER = 1
                null, new Integer(1)).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null).visitEnd();  // int compareTo(Object o)
        cw.visitEnd();
        byte[] b = cw.toByteArray();

        WriteUtil.saveToFile(b,"Comparable");

    }

}
