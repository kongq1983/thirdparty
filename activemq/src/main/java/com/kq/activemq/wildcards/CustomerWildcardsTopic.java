package com.kq.activemq.wildcards;

import com.kq.activemq.Util;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

/**
 * CustomerWildcardsTopic
 *
 * @author kq
 * @date 2019-08-05
 */
public class CustomerWildcardsTopic {

    public static void main(String[] args) throws Exception{

//        String queueName = "com.wildcard.topic.1";
        // 忽略abc  注意不能 com.>abc 要com.>.abc
        String queueName = "com.>.abc";

        ActiveMQConnectionFactory connectionFactory;
        Connection conn;
        Session session;
        MessageConsumer consumer;

        try {
            // brokerURL
            // http://activemq.apache.org/connection-configuration-uri.html
            // 1、创建连接工厂
            connectionFactory = new ActiveMQConnectionFactory(Util.ACTIVEMQ_URL);

            // 2、创建连接对象
            conn = connectionFactory.createConnection();
            conn.start(); // 一定要启动

            // 3、创建会话（可以创建一个或者多个session）
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            session = conn.createSession(false, Session.CLIENT_ACKNOWLEDGE);

            // 4、创建消息消费目标(Topic or Queue)
            Destination destination = session.createTopic(queueName);

            // 5、创建消息消费者 http://activemq.apache.org/destination-options.html
            consumer = session.createConsumer(destination);

//            consumer.setMessageListener(new AcknowledgeQueueListener());

            // 注册消息监听
            consumer.setMessageListener((message)->{

                // 6、接收消息(没有消息就持续等待)
                if (message instanceof TextMessage) {

                    try {
                        String text = ((TextMessage) message).getText();
                        System.out.println(Util.getNowTime()+",CustomerWildcardsTopic订阅者一收到的消息："+text );
                    }catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    System.out.println(message);
                }

            });


            TimeUnit.MINUTES.sleep(100);


            consumer.close();
            session.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
