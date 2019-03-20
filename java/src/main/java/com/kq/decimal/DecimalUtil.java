package com.kq.decimal;

import java.math.BigDecimal;

/**
 * DecimalUtil
 *
 * @author kq
 * @date 2019-03-20
 */
public class DecimalUtil {


    /**
     * 除
     * @param i1
     * @param i2
     * @return
     */
    public static BigDecimal divideForBigDecimal(BigDecimal i1, BigDecimal i2) {
        if(i1==null) return null;

        if(i2==null) return null;

        return i1.divide(i2,2,BigDecimal.ROUND_HALF_UP);

    }


    /**
     * 相乘
     * @param i1
     * @param i2
     * @return
     */
    public static BigDecimal multiply(BigDecimal i1,BigDecimal i2) {
        if(i1==null) return null;

        if(i2==null) return i1;

        return i1.multiply(i2);

    }

    /**
     * 相乘
     * @param i1
     * @param i2
     * @return
     */
    public static Double multiply(Number i1,Number i2) {
        if(i1==null) return null;

        if(i2==null) return null;

        return i1.doubleValue()*i2.doubleValue();

    }


    public static BigDecimal add(BigDecimal v1,BigDecimal v2){

        if(v1==null) v1 = new BigDecimal(0);
        if(v2==null) v2 = new BigDecimal(0);

        return v1.add(v2);
    }

    public static Double add(Number v1,Number v2){

        if(v1==null) v1 = 0;
        if(v2==null) v2 = 0;

        return v1.doubleValue()+v2.doubleValue();
    }


    public static void main(String[] args) {

        System.out.println("除法:"+ divideForBigDecimal(new BigDecimal("123456.18"),new BigDecimal(100)));
    }

}
