package com.kq.reactor.flux.demo1;

import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import java.util.List;

/**
 * FluxDemo1
 *
 * @author kq
 * @date 2019-09-17
 */
public class FluxDemo1 {

    public static void main(String[] args) {

        UnicastProcessor<Integer> processor = UnicastProcessor.create();

//        Flux<String> bridge = Flux.create(sink -> {
//            myEventProcessor.register(
//                    new MyEventListener<String>() {
//                        @Override
//                        public void onDataChunk(List<String> chunk) {
//                            for(String s : chunk) {
//                                sink.next(s);
//                            }
//                        }
//                        @Override
//                        public void processComplete() {
//                            sink.complete();
//                        }
//                    });
//        });
    }

}
