package com.kq.rxjava3.demo;

import com.kq.rxjava3.DataUtil;
import com.kq.rxjava3.entity.Inventory;
import io.reactivex.rxjava3.core.Flowable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * FlowableFlatMapDemo
 *
 * @author kq
 * @date 2019-12-02
 */
public class FlowableFlatMapDemo {

    public static void main(String[] args) {
        flatMap1();
    }

    public static void flatMap1() {
        List<Inventory> list = DataUtil.getInventoys();

        list.stream()
                .flatMap(i-> DataUtil.getInventoryDetails(i.getId()).stream().map(n->n.getId())).collect(Collectors.toList())
                    .forEach(System.out::println);

        System.out.println("---------------------------------------------------------");


    }

    public static void flatMap2() {
        List<Inventory> list = DataUtil.getInventoys();

        Flowable<Inventory> flowable = Flowable.fromArray((Inventory[])list.toArray());

//        flowable
//                .flatMap(i-> DataUtil.getInventoys().stream().map(n->n.getId())).collect(Collectors.toList())
//                .subscribe(System.out::println);

        System.out.println("---------------------------------------------------------");


    }

}
