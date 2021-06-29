package com.kq.guava.bloomfilter;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author kq
 * @date 2021-06-29 13:51
 * @since 2020-0630
 */
public class BloomFilterTest {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-","");
    }

    public static void main(String[] args) {

        int size = 100000;
        // expectedInsertions 这个要比size大
        BloomFilter bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8),2*size);
//
        List<String> list = new ArrayList<>();
//        UUID uuid = UUID.randomUUID();
//
        for(int i=0;i<size;i++) {
//            System.out.println("---------------------"+i);
//            String id = uuid();
            String id = String.valueOf(i);
            list.add(id);

            bloomFilter.put(id);
        }

        for(String id : list) {
            boolean result = bloomFilter.mightContain(id);
            if(!result){
                System.out.println("id="+id+", is not exists!");
            }
        }

        String noStr = "no!";
        boolean result = bloomFilter.mightContain(noStr);
        System.out.println("no result = "+result);
        if(!result){
            System.out.println("id="+noStr+", is not exists!");
        }

//        System.out.println();

    }


}
