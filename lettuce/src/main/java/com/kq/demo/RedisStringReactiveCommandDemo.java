package com.kq.demo;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.reactive.RedisStringReactiveCommands;
import reactor.core.publisher.Flux;

/**
 * RedisStringReactiveCommandDemo
 *
 * @author kq
 * @date 2019-12-03
 */
public class RedisStringReactiveCommandDemo {

    public static void main(String[] args) {
        RedisClient client = RedisClient.create(Server.SERVER);
        RedisStringReactiveCommands<String, String> commands = client.connect().reactive();

        commands.get("name").subscribe(System.out::println);

        Flux.just("name", "key", "age").
                flatMap(key -> commands.get(key)).
                subscribe(value -> System.out.println("Got value: " + value));

        System.out.println("----------------------------------");


        Flux.just("name", "key", "age")
                .flatMap(commands::get).
//                flatMap(value -> commands.rpush("result", value)).
                subscribe(value -> System.out.println("-- Got value: " + value));

    }

}
