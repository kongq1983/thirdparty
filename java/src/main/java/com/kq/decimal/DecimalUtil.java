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


    public static void main(String[] args) {

        System.out.println("除法:"+ divideForBigDecimal(new BigDecimal("123456.18"),new BigDecimal(100)));
    }

}
