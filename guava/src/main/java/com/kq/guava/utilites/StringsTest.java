package com.kq.guava.utilites;

import com.google.common.base.Strings;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * StringsTest
 *
 * @author kq
 * @date 2019-03-22
 */
public class StringsTest {


    @Test
    public void testStringRepeat() {

        String str = "abc";
        String result = Strings.repeat(str, 3);
        assertThat("abcabcabc", equalTo(result));

    }

    @Test
    public void testPadStart() {
        String result = Strings.padStart("7", 3, '0');

        assertThat("007", equalTo(result));

        result = Strings.padStart("18", 5, '0');

        assertThat("00018", equalTo(result));

        result = Strings.padStart("18.", 5, '0');

        assertThat("0018.", equalTo(result));
    }


    @Test
    public void testPadEnd() {

        String result = Strings.padEnd("4.", 5, '0');
        assertThat("4.000", equalTo(result));


        result = Strings.padEnd("8.0", 5, '0');
        assertThat("8.000", equalTo(result));

    }
}
