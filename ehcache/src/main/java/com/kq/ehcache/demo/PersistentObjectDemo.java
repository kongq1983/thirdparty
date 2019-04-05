package com.kq.ehcache.demo;

import com.kq.ehcache.entity.Student;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

import java.time.Duration;
import java.util.ArrayList;

/**
 * 注意对象要实现序列化，否则没数据
 * Created by qikong on 2019/4/5.
 */
public class PersistentObjectDemo {

    static CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
            .withCache("mylistcache",
                    CacheConfigurationBuilder.newCacheConfigurationBuilder
                            (String.class,ArrayList.class,
                                    ResourcePoolsBuilder.heap(100).disk(100, MemoryUnit.MB,true)).
                            withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(30000))))
            .with(CacheManagerBuilder.persistence(PersistentDemo.SAVE_PATH)).build(true);

    public static void save(){

        ArrayList<Student> list = new ArrayList();
        for(int i=0;i<5;i++) {
            Student s = new Student();
            s.setAge(i);
            s.setId(String.valueOf(i));
            s.setName("name+"+i);
            list.add(s);
        }

        Cache<String,ArrayList> cache = cacheManager.getCache("mylistcache",String.class,ArrayList.class);

        cache.put("studentList",list);

        ArrayList data = cache.get("studentList");
        System.out.println("data="+data);


    }


    public static void main(String[] args) {
//        save();

        Cache<String,ArrayList> cache = cacheManager.getCache("mylistcache",String.class,ArrayList.class);


        ArrayList list = cache.get("studentList");

        System.out.println(list);

        //不要忘记close  否则有可能不会刷新到磁盘
        cacheManager.close();

    }


}
