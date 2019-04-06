package com.kq.ehcache.config;

import org.ehcache.Cache;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by qikong on 2019/4/6.
 */
public class EhcacheConfigTest {


    @Test
    public void testGet(){
        EhcacheConfig c = new EhcacheConfig();

        Cache<String,Long> cache = c.getCache("sizeCache",String.class,Long.class);

        cache.put("king",100l);

        Long size = cache.get("king");

        assertEquals(size.longValue(),100l);

    }

}
