package com.kq.asm.demo.simple;

import com.kq.asm.util.WriteUtil;
import org.objectweb.asm.*;

import java.lang.annotation.Annotation;

/**
 * @author kq
 * @date 2022-07-18 15:47
 * @since 2020-0630
 */
public class FieldDemo extends ClassLoader implements Opcodes {


    public static void main(String[] args) throws Exception{
        // 创建一个ClassWriter, 以生成一个新的类

        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_6, ACC_PUBLIC, "com/pansoft/espdb/bean/Person", null, "java/lang/Object", null);



        /*********************************constructor**********************************************/

        MethodVisitor mw = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null,
                null);
        mw.visitVarInsn(ALOAD, 0);
        mw.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        mw.visitInsn(RETURN);
        mw.visitMaxs(1, 1);
        mw.visitEnd();


        /*************************************field******************************************/

        //生成String name字段
        FieldVisitor fv = cw.visitField(ACC_PUBLIC, "name", "Ljava/lang/String;", null, null);
//        AnnotationVisitor av = fv.visitAnnotation("LNotNull;", true);
        AnnotationVisitor av = fv.visitAnnotation("Lcom/kq/asm/demo/simple/NotNull;", true);
        av.visit("value", "abc");
        av.visitEnd();
        fv.visitEnd();



        /***********************************generate and load********************************************/

        byte[] code = cw.toByteArray();

        WriteUtil.saveToFile(code,"Person.class");

        FieldDemo loader = new FieldDemo();
        Class<?> clazz = loader.defineClass(null, code, 0, code.length);


        /***********************************test********************************************/

        Object beanObj = clazz.getConstructor().newInstance();

        clazz.getField("name").set(beanObj, "king");

        String nameString = (String) clazz.getField("name").get(beanObj);
        System.out.println("filed value : " + nameString);

//        String annoVal = clazz.getField("name").getAnnotation(NotNull.class).value();
//        System.out.println("annotation value: " + annoVal);

        Annotation[] as = clazz.getField("name").getAnnotations();
        System.out.println("as="+as);
        if(as!=null) {
            for(Annotation a : as) {
                System.out.println("a="+a);
            }
        }


    }


}
