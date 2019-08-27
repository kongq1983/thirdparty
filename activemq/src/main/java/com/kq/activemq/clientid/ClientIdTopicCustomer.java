package com.kq.activemq.clientid;

import com.kq.activemq.Util;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ClientIdTopicCustomer
 *
 * @author kq
 * @date 2019/8/4
 */
public class ClientIdTopicCustomer {

    public static final String TOPIC_NAME = "com.client.id.topic";
    public static final String CLIENT_ID = "topic.client1";

    public static void main(String[] args) {
        ActiveMQConnectionFactory connectionFactory;
        Connection conn;
        Session session;

        try {
            // 1、创建连接工厂
            connectionFactory = new ActiveMQConnectionFactory(Util.ACTIVEMQ_URL);

            // 2、创建连接对象
            conn = connectionFactory.createConnection();

            // 通过conn对象，持久订阅需指定ClientId
            conn.setClientID(CLIENT_ID);

            conn.start(); // 一定要启动

            // 3、创建会话（可以创建一个或者多个session）
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // 4、创建消息消费目标(Topic or Queue)
            Topic destination = session.createTopic(TOPIC_NAME);

            // 5、创建消息消费者 http://activemq.apache.org/destination-options.html
            // 第2个参数  Subscription Name
            TopicSubscriber consumer = session.createDurableSubscriber(destination, "I love Durable");
//            TopicSubscriber consumer = session.createDurableSubscriber(destination, CLIENT_ID);

            // 6、异步接收消息
            consumer.setMessageListener(new MessageListener() {

                @Override
                public void onMessage(Message message) {
                    if (message instanceof TextMessage) {
                        try {
                            System.out.println("Time: " + System.currentTimeMillis() + " 收到文本消息："
                                    + ((TextMessage) message).getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(message);
                    }
                }
            });

            // consumer.close();
            // session.close();
            // conn.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
