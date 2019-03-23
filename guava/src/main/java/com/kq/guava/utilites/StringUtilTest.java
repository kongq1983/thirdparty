package com.kq.guava.utilites;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by qikong on 2019/3/23.
 */
public class StringUtilTest {

    @Test
    public void testStringMethod(){
        assertThat(Strings.emptyToNull(""),nullValue());
        assertThat(Strings.emptyToNull("   "),notNullValue());
        assertThat(Strings.nullToEmpty(null),equalTo(""));
        assertThat(Strings.commonPrefix("Hello","How"),equalTo("H"));
        assertThat(Strings.commonPrefix("Hello","Good"),equalTo(""));

        assertThat(Strings.repeat("abc",3),equalTo("abcabcabc"));

        assertThat(Strings.isNullOrEmpty(null),equalTo(true));
        assertThat(Strings.isNullOrEmpty(""),equalTo(true));
        assertThat(Strings.isNullOrEmpty("   "),equalTo(false));

        assertThat(Strings.padStart("10.",5,'0'),equalTo("0010."));
        assertThat(Strings.padEnd("10.",5,'0'),equalTo("10.00"));


    }


    @Test
    public void testMatch(){
        assertThat(CharMatcher.is('A').countIn("Abc Good A Hello"),equalTo(2));

    }

}
