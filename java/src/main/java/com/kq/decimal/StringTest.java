package com.kq.decimal;

import java.util.HashSet;
import java.util.Set;

/**
 * StringTest
 *
 * @author kq
 * @date 2019-03-20
 */
public class StringTest {

    public static void main(String[] args) {
//        String result = "A";
//        if(!result.trim().matches("[a-zA-Z]")){
//            System.out.println(result);
//        }

        String str0 = "java";
        String str1 = "java";

        String str2 = new String("java");

        boolean result = (str0 ==str1);
        System.out.println(result);
        boolean result1 = (str2 ==str1);
        System.out.println(result1);

        Integer n1 = 127;
        Integer n2 = 127;

        System.out.println(n1==n2);


        Integer n11 = 128;
        Integer n22 = 128;

        System.out.println(n11==n22);

        int num = Integer.parseInt("10a",16);
        System.out.println(num);

        Set<String> set = new HashSet();
        set.add("one");
        set.add("one");
        set.add("two");
        set.add("two");

        System.out.println(set.size());

//        rownum


    }

}
