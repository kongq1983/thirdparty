package com.kq.demo;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * RedisAsyncCommandDemo
 *
 * @author kq
 * @date 2019-12-03
 */
public class RedisAsyncCommandDemo {

    public static void main(String[] args) throws Exception{
        RedisClient client = RedisClient.create(Server.SERVER);
        RedisAsyncCommands<String, String> commands = client.connect().async();

        RedisFuture<String> future = commands.get("key");
        String value = future.get();
        System.out.println("key="+value);

        println();
        println1();
        println2();
        println3();

    }

    public static void println(){
        RedisClient client = RedisClient.create(Server.SERVER);
        RedisAsyncCommands<String, String> commands = client.connect().async();
        try {
            RedisFuture<String> future = commands.get("key");
            String value = future.get(1, TimeUnit.MINUTES);
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void println1(){
        RedisClient client = RedisClient.create(Server.SERVER);
        RedisAsyncCommands<String, String> commands = client.connect().async();
        RedisFuture<String> future = commands.get("key");

        future.thenAccept(new Consumer<String>() {
            @Override
            public void accept(String value) {
                System.out.println(value);
            }
        });
    }

    public static void println2(){
        RedisClient client = RedisClient.create(Server.SERVER);
        RedisAsyncCommands<String, String> commands = client.connect().async();
        RedisFuture<String> future = commands.get("key");

        future.thenAccept(s -> System.out.println(s));
    }

    public static void println3(){
        RedisClient client = RedisClient.create(Server.SERVER);
        RedisAsyncCommands<String, String> commands = client.connect().async();
        RedisFuture<String> future = commands.get("key");

        future.thenAccept(System.out::println);
    }

}
