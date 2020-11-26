package com.kq.kafka.demo;

import com.kq.kafka.util.KafkaUtil;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerSample {

    public static void main(String[] args) {
        String topic = KafkaUtil.TEST_TOPIC;

        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaUtil.SERVER);
        props.put("group.id","testGroup1"); //分组id
        // 用于设置自动提交offset到zookeeper的时间间隔 单位毫秒
        props.put("enable.commit.interval.ms","1000");

        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
//        props.put("zk.connect","mq.server1.com:2181");


        Consumer<String,String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList(topic));

        while (true) {
            ConsumerRecords<String,String> records = consumer.poll(100);

            for(ConsumerRecord<String,String> record : records) {
                System.out.printf("partition=%d,offset=%d,key=%s,value=%s%n",record.partition(),record.offset(),record.key(),record.value());
            }

        }

    }

}
