package com.kq.guava.utilites;

import com.google.common.base.Preconditions;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;


/**
 * Created by qikong on 2019/3/22.
 */
public class PrecondtionsTest {


    @Test(expected = NullPointerException.class)
    public void checkNotNull0() {

        Preconditions.checkNotNull(null);


    }


    @Test
    public void checkNotNull1(){


        try{
            this.checkNotNullWithFormatMessage(null);
        }catch (Exception e){
//            assertThat(e,is(NullPointerException.class));
            assertThat(e instanceof  NullPointerException ,equalTo(true));
            assertThat(e.getMessage(),equalTo("size must be 2"));

        }

    }


    private void checkNotNullWithFormatMessage(final List<String> list) {

        Preconditions.checkNotNull(list,"size must be %s",2);


    }

}
