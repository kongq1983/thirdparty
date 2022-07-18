package com.kq.asm.util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author kq
 * @date 2022-07-18 15:52
 * @since 2020-0630
 */
public class WriteUtil {

    /**
     *
     * @param code
     * @param fileName   "Example.class"
     * @throws Exception
     */
    public static void saveToFile(byte[] code,String fileName) throws Exception{

        fileName = fileName.replace(".class","");

        try(FileOutputStream fos = new FileOutputStream(new File("D:\\log",fileName+".class"))) {
            fos.write(code);
        }

    }

}
