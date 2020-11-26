package com.kq.reactor.flux.demo1;

import java.util.List;

/**
 * MyEventListener
 *
 * @author kq
 * @date 2019-09-17
 */
public interface MyEventListener<T> {

    void onDataChunk(List<T> chunk);
    void processComplete();

}
