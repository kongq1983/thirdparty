package com.kq.reactor.flux;

import reactor.core.publisher.Flux;

/**
 * FluxDemo3
 *
 * @author kq
 * @date 2019-09-17
 */
public class FluxDemo3 {

    public static void main(String[] args) {
//        Flux.create(sink -> {
//            //向下游发布元素
//            sink.next("helloword");
//            sink.next("helloword2");
//            //结束发布元素
//            sink.complete();
//        }).subscribe(System.out::println);//subscribe发布消息，System.out.println为消费者，消费消息;


        Flux flux = Flux.create(sink -> {
            //向下游发布元素
            sink.next("2-helloword");
            sink.next("2-helloword2");
            //结束发布元素
            sink.complete();
        });


        flux.subscribe(System.out::println);
//        flux.subscribe(System.out::println);

    }

}
