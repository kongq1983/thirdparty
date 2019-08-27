package com.kq.kafka.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;

public class ProducerSample {

    public static void main(String[] args) {
        Map<String,Object> props = new HashMap<>();
        props.put("bootstrap.servers","mq.server1.com:9092");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("zk.connect","mq.server1.com:2181");

        String topic = "test-topic";

        Producer<String,String> producer = new KafkaProducer<String, String>(props);
        System.out.println("send 1.");
        producer.send(new ProducerRecord<String,String>(topic,"idea-key2","java-message-1"));
        System.out.println("send 2.");
        producer.send(new ProducerRecord<String,String>(topic,"idea-key2","java-message-2"));
        System.out.println("send 3.");
        producer.send(new ProducerRecord<String,String>(topic,"idea-key2","java-message-3"));
        System.out.println("start colse.");
        producer.close();

        System.out.println("the end.");

    }

}
