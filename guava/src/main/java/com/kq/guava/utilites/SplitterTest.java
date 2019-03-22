package com.kq.guava.utilites;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;


/**
 * Created by qikong on 2019/3/22.
 */
public class SplitterTest {


    @Test
    public void testSplitOnSplit(){
        List<String> list = Splitter.on(" ").splitToList("hello world welcome");

        assertThat(list.size(),equalTo(3));
        assertThat(list.get(0),equalTo("hello"));
        assertThat(list.get(1),equalTo("world"));
        assertThat(list.get(2),equalTo("welcome"));

    }

    @Test
    public void testSplit1(){
        List<String> list = Splitter.on("|").splitToList("hello|world|welcome||");

        assertThat(list.size(),equalTo(5));


        List<String> omitList = Splitter.on("|").omitEmptyStrings().splitToList("hello|world|welcome||");

        assertThat(list.size(),equalTo(3));

    }

    @Test
    public void tesetTrimResult(){
        List<String> list = Splitter.on("|").splitToList("hello | world| welcome");
        assertThat(list.get(0),equalTo("hello "));
        assertThat(list.get(1),equalTo(" world"));
        assertThat(list.get(2),equalTo(" welcome"));


        list = Splitter.on("|").trimResults().omitEmptyStrings().splitToList("hello | world| welcome");

        assertThat(list.get(0),equalTo("hello"));
        assertThat(list.get(1),equalTo("world"));
        assertThat(list.get(2),equalTo("welcome"));


    }


    @Test
    public void testSplitFixLength(){

        List<String> list = Splitter.fixedLength(3).splitToList("aaabbbccc111222888");

        assertThat(list.get(0),equalTo("aaa"));
        assertThat(list.get(1),equalTo("bbb"));
        assertThat(list.get(2),equalTo("ccc"));

    }


    @Test
    public void testSplitLimit(){

        List<String> list = Splitter.on("#").limit(3).splitToList("aaa#bbb#ccc#111#222#888");

        assertThat(list.size(),equalTo(3));
        assertThat(list.get(0),equalTo("aaa"));
        assertThat(list.get(1),equalTo("bbb"));
        assertThat(list.get(2),equalTo("ccc#111#222#888"));

    }

    @Test
    public void tesetOnPattern(){

        List<String> list = Splitter.onPattern("\\|-").omitEmptyStrings().splitToList("hello|world-welcome");

        assertThat(list.size(),equalTo(3));

    }


    @Test
    public void testSplitOnToMap(){

        String str = "hello=HELLO| world=WORLD|||";

        //{hello=HELLO, world=WORLD}
        Map<String,String> map = Splitter.onPattern("\\|").trimResults().omitEmptyStrings().withKeyValueSeparator("=").split(str);


        assertThat(map.size(),equalTo(2));
        assertThat(map.containsKey("hello"),equalTo(true));
        System.out.println(map);

    }


}
