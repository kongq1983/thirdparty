package com.kq.decimal;

/**
 * StringTest
 *
 * @author kq
 * @date 2019-03-20
 */
public class StringTest {

    public static void main(String[] args) {
        String result = "A";
        if(!result.trim().matches("[a-zA-Z]")){
            System.out.println(result);
        }
    }

}
