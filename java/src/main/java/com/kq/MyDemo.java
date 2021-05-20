package com.kq;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.List;

/**
 * @author kq
 * @date 2021-05-20 12:29
 * @since 2020-0630
 */
public class MyDemo {

    public static void main(String[] args) throws Exception{
        InputStream in = MyDemo.class.getResourceAsStream("/mytxt");
        System.out.println("in=" + in);

        List<String> list = IOUtils.readLines(in,"utf-8");

        int index = 0;

        StringBuilder sb = new StringBuilder("(");
        for(String str : list) {
            String[] ss = str.split(",");

            for(String s : ss) {
                String newStr = "'"+s+"',";
//                System.out.println("s="+s);
                sb.append(newStr);
                index++;
            }

        }
        sb.append(")");

        System.out.println(sb);
        System.out.println("index="+index);

    }

}
