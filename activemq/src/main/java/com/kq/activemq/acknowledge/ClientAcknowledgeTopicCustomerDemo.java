package com.kq.activemq.acknowledge;

import com.kq.activemq.Util;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ClientAcknowledgeCustomerDemo
 *
 * @author kq
 * @date 2019-08-01
 */
public class ClientAcknowledgeTopicCustomerDemo {


    public static void main(String[] args) {
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
//            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            session = conn.createSession(false, Session.CLIENT_ACKNOWLEDGE);

            // 4、创建消息消费目标(Topic or Queue)
            Destination destination = session.createTopic(ClientAcknowledgeTopicProducerDemo.TOPIC_NAME);

            // 5、创建消息消费者 http://activemq.apache.org/destination-options.html
            consumer = session.createConsumer(destination);

            // 6、接收消息(没有消息就持续等待)
            Message message = consumer.receive();
            if (message instanceof TextMessage) {
                System.out.println("Topic 收到文本消息：" + ((TextMessage) message).getText());
            } else {
                System.out.println("Topic "+message);
            }

            // Session.CLIENT_ACKNOWLEDGE 模式 , 需要调用acknowledge ,否则队列不会清除
            message.acknowledge();

            consumer.close();
            session.close();
            conn.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
