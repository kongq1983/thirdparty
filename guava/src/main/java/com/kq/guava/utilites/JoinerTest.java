package com.kq.guava.utilites;

import com.google.common.base.Joiner;
import org.junit.Test;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;


import java.util.Arrays;
import java.util.List;

/**
 * Created by qikong on 2019/3/21.
 */
public class JoinerTest {

    List<String> list = Arrays.asList("Google","Java","Groovy",null,"Scala",null,"Spark");

    @Test(expected = NullPointerException.class)
    public void testJoin(){
        String str = Joiner.on("-").join(list);

        System.out.println(str);

    }


    /**
     * 忽略null
     */
    @Test
    public void testJoinWithSkipNullValue(){
        String str = Joiner.on("-").skipNulls().join(list);

        System.out.println(str);

        assertThat(str,equalTo("Google-Java-Groovy-Scala-Spark"));

    }


    @Test
    public void testJoinWithDefaultValue(){
        String str = Joiner.on("-").useForNull("").join(list);

        System.out.println(str);

        assertThat(str,equalTo("Google-Java-Groovy--Scala--Spark"));

    }


    @Test
    public void testJoinOnAppendToStringBuilder(){
        final StringBuilder builder = new StringBuilder();

        StringBuilder resultBuilder = Joiner.on("-").useForNull("").appendTo(builder,list);

        //builder 和 resultBuilder 同个对象

        assertThat(resultBuilder,sameInstance(builder));

        assertThat(resultBuilder.toString(),equalTo("Google-Java-Groovy--Scala--Spark"));
    }

}
