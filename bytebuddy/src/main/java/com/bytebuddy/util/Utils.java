package com.bytebuddy.util;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author kq
 * @date 2022-09-16 11:08
 * @since 2020-0630
 */
public class Utils {

    public static void outClass(byte[] bytes,String className) {

        FileOutputStream out = null;
        try {
//            String pathName = Utils.class.getResource("/").getPath() + className+".class";

            // E:\githubproject\thirdparty\bytebuddy\out
            String pathName = new File(new File(Utils.class.getResource("/").getPath()).getParent()).getParent()+File.separator+"out";
            out = new FileOutputStream(new File(pathName,className+".class"));
            System.out.println("类输出路径：" + pathName);
            out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    public static void demo() {
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .name("com.kq.test.HelloWorld")
                .make();

        // 输出类字节码
        outClass(dynamicType.getBytes(),"HelloWorld");
    }

    public static void main(String[] args) {
        demo();
    }

}
