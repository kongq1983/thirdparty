package com.kq.asm.demo.generate;

import com.kq.asm.util.WriteUtil;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

/**
 * 字段和构造方法
 * @author kq
 * @date 2022-07-18 17:11
 * @since 2020-0630
 */
public class GenerateClassFieldAndConstructor {

    // public class PersonEntity extends BaseEntity {
    // public static final int size = 100;
    // private Long id;
    // private String name;
    // private int age;

    // public PersonEntity() {}

    // public PersonEntity(int id) {
    //   this.id = id;
    // }

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

        // 默认构造函数
        MethodVisitor mv1 = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv1.visitCode();
        mv1.visitVarInsn(ALOAD, 0);
        mv1.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv1.visitInsn(RETURN);
        mv1.visitMaxs(1, 1);
        mv1.visitEnd();


        // 多个构造函数
        MethodVisitor mv2 = cw.visitMethod(ACC_PUBLIC, "<init>", "(Ljava/lang/Long;java/lang/String;)V", null, null);
        mv2.visitCode();
        mv2.visitVarInsn(ALOAD, 0);
        mv2.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "(Ljava/lang/Long;java/lang/String;)V", false);
        mv2.visitInsn(RETURN);
        mv2.visitMaxs(1, 1);
        mv2.visitEnd();


        cw.visitEnd();
        byte[] b = cw.toByteArray();

        WriteUtil.saveToFile(b,"PersonEntity1");

    }

}
