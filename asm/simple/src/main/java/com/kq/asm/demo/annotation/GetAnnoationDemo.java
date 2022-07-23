package com.kq.asm.demo.annotation;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author kq
 * @date 2022-07-23 11:02
 * @since 2020-0630
 */
public class GetAnnoationDemo extends ClassLoader implements Opcodes {
    public static void main(String[] args) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, IOException {
        String n = User.class.getName();
        ClassReader cr = new ClassReader(n);
        final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        ClassNode cn =  new ClassNode();
        cr.accept(cn, ASM4);
        final List<AnnotationNode> nlist = cn.visibleAnnotations;


        cr.accept(new ClassVisitor(ASM4,cw) {
            @Override
            public AnnotationVisitor visitAnnotation(String desc,
                                                     boolean visible) {
                AnnotationNode an = new AnnotationNode(desc);
                nlist.add(an);
                return an;
            }
        }, 2);

        for (AnnotationNode annotationNode : nlist) {
            List vl = annotationNode.values;
            System.out.println("annotationNode:"+annotationNode.desc+":"+vl.get(0).toString() + ":"+vl.get(1));
        }
    }

}
