package com.kq.activemq.transaction;

import com.kq.activemq.Util;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ProducerTransactionTopic
 *
 * @author kq
 * @date 2019-08-01
 */
public class ProducerTransactionTopic {

    public static final String TOPIC_NAME = "transaction.topic.1";

    public static void main(String[] args) {

        ActiveMQConnectionFactory connectionFactory;
        Connection conn;
        Session session;

        try {
            // 1、创建连接工厂
            connectionFactory = new ActiveMQConnectionFactory(Util.ACTIVEMQ_URL);

            // 2、创建连接
            conn = connectionFactory.createConnection();
            conn.start(); // 一定要start

            // 3、创建会话（可以创建一个或者多个session）
//            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE); acknowledge
            session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);

            // 4、创建消息发送目标 (Topic or Queue)
            Destination destination = session.createTopic(TOPIC_NAME);

            // 5、用目的地创建消息生产者
            MessageProducer producer = session.createProducer(destination);
            // 设置递送模式(持久化 / 不持久化)
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            // 6、创建一条文本消息
            String text = "Hello world! From: " + Thread.currentThread().getName() + " : "
                    + System.currentTimeMillis();
            TextMessage message = session.createTextMessage(text);

            // 7、通过producer 发送消息
            System.out.println("Sent message: " + text);
            producer.send(message);

            //提交事务  commit后，才能看到在服务器看到数据
            session.commit();

            // 8、 清理、关闭连接
            session.close();
            conn.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
