package com.kq.activemq.acknowledge.queue;

import com.kq.activemq.Util;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.lang3.StringUtils;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

/**
 * ClientAcknowledgeCustomerListener
 *  setMessageListener  queue格式 只能一个监听器收到
 * @author kq
 * @date 2019-08-01
 */
public class ClientAcknowledgeCustomerListenerDemo {


    public static void main(String[] args) throws Exception{
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
            Destination destination = session.createQueue(ClientAcknowledgeProducerDemo.QUEUE_NAME);

            // 5、创建消息消费者 http://activemq.apache.org/destination-options.html
            consumer = session.createConsumer(destination);

//            consumer.setMessageListener(new AcknowledgeQueueListener());

            // 注册消息监听
            consumer.setMessageListener((message)->{

                // 6、接收消息(没有消息就持续等待)
            if (message instanceof TextMessage) {

                try {
                    String text = ((TextMessage) message).getText();
                    if (StringUtils.startsWith(text, "acknowledge")) {
                        System.out.println(" -- 确认调用acknowledge ");
                        // Session.CLIENT_ACKNOWLEDGE 模式 , 需要调用acknowledge ,否则队列不会清除
                        message.acknowledge();
                    } else {
                        //通知mq进行重发  最多重发六次  总共7次
                        System.out.println(" 调用recover ");
                        session.recover();
                    }
                    System.out.println(" Acknowledge 订阅者一收到的消息："+text );
                }catch (Exception e) {
                    e.printStackTrace();
                }


            } else {
                System.out.println(message);
            }

            });


            TimeUnit.MINUTES.sleep(10);


            consumer.close();
            session.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
