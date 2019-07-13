package com.kq.demo;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * FirstDemo
 *
 * @author kq
 * @date 2019/7/13
 */
public class HelloWorld {

    public static void main(String[] args) {
//        RedisClient redisClient = RedisClient.create("redis://password@localhost:6379/0");
        RedisClient redisClient = RedisClient.create("redis://192.168.3.200:6379");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();

        syncCommands.set("key", "Hello, Redis!");

        String value = syncCommands.get("key");
        System.out.println("value="+value);

        connection.close();
        redisClient.shutdown();
    }

}
