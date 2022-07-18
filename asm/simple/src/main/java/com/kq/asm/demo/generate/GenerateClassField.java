package com.kq.asm.demo.generate;

import com.kq.asm.util.WriteUtil;
import org.objectweb.asm.ClassWriter;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.ACC_INTERFACE;

/**
 * @author kq
 * @date 2022-07-18 17:11
 * @since 2020-0630
 */
public class GenerateClassField {

    // public class PersonEntity extends BaseEntity {
    // public static final int size = 100;
    // private Long id;
    // private String name;
    // private int age;
    // }


    public static void main(String[] args) throws Exception{
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_8, ACC_PUBLIC ,
                "com/kq/asm/demo/generate/PersonEntity", null, "com/kq/asm/demo/generate/BaseEntity",
                new String[] {});  // new String[] {  } 是接口

        // public static final int size = 100;
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "size", "I",
                null, new Integer(100)).visitEnd();

        // private Long id;
        cw.visitField(ACC_PRIVATE , "id", "Ljava/lang/Long;",
                null, null).visitEnd();

        // private String name;
        cw.visitField(ACC_PRIVATE , "name", "Ljava/lang/String;",
                null, null).visitEnd();

        // private int age;
        cw.visitField(ACC_PRIVATE , "age", "I",
                null, null).visitEnd();

        cw.visitEnd();
        byte[] b = cw.toByteArray();

        WriteUtil.saveToFile(b,"PersonEntity");

    }

}
