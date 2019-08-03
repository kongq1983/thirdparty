package com.kq.activemq.delay;

import com.kq.activemq.Util;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

/**
 * DelayFixedTimeProducer
 *
 * @author kq
 * @date 2019/8/3
 */
public class DelayFixedTimeProducer {

    public static final String DELAY_QUEUE = "com.delay.fixed.time.queue";

    public static void main(String[] args) {

        ActiveMQConnectionFactory connectionFactory;
        Connection conn;
        Session session;

        try {
            // 1、创建连接工厂
            connectionFactory = new ActiveMQConnectionFactory(Util.ACTIVEMQ_URL);
            connectionFactory.setUseAsyncSend(true);
            // 2、创建连接
            conn = connectionFactory.createConnection();
            conn.start(); // 一定要start

            // 3、创建会话（可以创建一个或者多个session）
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // 4、创建消息发送目标 (Topic or Queue)
            Destination destination = session.createQueue(DELAY_QUEUE);

            // 5、用目的地创建消息生产者
            MessageProducer producer = session.createProducer(destination);
            // 设置递送模式(持久化 / 不持久化)
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            // 6、创建文本消息
            // http://activemq.apache.org/delay-and-schedule-message-delivery.html

            for(int  i=1;i<=10;i++) {

                String messageStr = "delay 120s message - "+i;

                // 延时 120秒
                TextMessage message = session.createTextMessage(messageStr);
                message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 120 * 1000L);

                System.out.println(Util.getNowTime() + ",send " + messageStr);

                // 7、发送消息
                producer.send(message);

                Thread.sleep(1000l);
            }

            System.out.println("Sent delay fixed time message: ok");

            TimeUnit.MINUTES.sleep(10);

            // 8、 清理、关闭连接
            session.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
