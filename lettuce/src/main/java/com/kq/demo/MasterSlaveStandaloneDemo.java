package com.kq.demo;

import io.lettuce.core.ReadFrom;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.codec.Utf8StringCodec;
import io.lettuce.core.masterslave.MasterSlave;
import io.lettuce.core.masterslave.StatefulRedisMasterSlaveConnection;

/**
 * MasterSlaveStandaloneDemo
 * 优先从slave读取
 * @author kq
 * @date 2019/7/13
 */
public class MasterSlaveStandaloneDemo {

    public static void main(String[] args) {

        RedisClient redisClient = RedisClient.create();

        StatefulRedisMasterSlaveConnection<String, String> connection = MasterSlave.connect(redisClient, new Utf8StringCodec(),
                RedisURI.create(Server.SERVER));
//        connection.setReadFrom(ReadFrom.MASTER_PREFERRED);
        connection.setReadFrom(ReadFrom.SLAVE_PREFERRED);

        System.out.println("Connected to Redis");

        RedisCommands<String, String> syncCommands = connection.sync();

        syncCommands.set("address", "zhejiang");

        String value = syncCommands.get("key");
        System.out.println("value="+value);

        connection.close();
        redisClient.shutdown();

    }

}
