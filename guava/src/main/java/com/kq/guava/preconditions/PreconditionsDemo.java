package com.kq.guava.preconditions;

import com.google.common.base.Preconditions;

/**
 * @author kq
 * @date 2021-07-23 11:11
 * @since 2020-0630
 */
public class PreconditionsDemo {

    public static void main(String[] args) {

        String name = "";

        Preconditions.checkNotNull(name,"name must be not null !");

        int state = 5;
        Preconditions.checkState(state==5,"state must be 5");

        Preconditions.checkArgument(state==5,"argument state must be 5");

        int index = 3;
        int size = 10;
        // check  index < 0 || index > size
        Preconditions.checkPositionIndex(index,size,"index must between 0 - size ");

        // index < 0 || index >= size
        Preconditions.checkElementIndex(index,size,"index must between 0 - (size-1) ");



    }


}
