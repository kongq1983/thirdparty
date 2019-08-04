package com.kq.activemq.delay;

import com.kq.activemq.Util;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

/**
 * DelayRepeatProducer
 *
 * @author kq
 * @date 2019/8/4
 */
public class DelayRepeatProducer {

    public static final String DELAY_QUEUE = "com.delay.repeat.queue";

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

            String messageStr = "delay 30s repeat message - ";

            // 延时 30秒
            TextMessage message = session.createTextMessage(messageStr);
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 30 * 1000L); //延时
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, 2 * 1000L); // 投递间隔
            message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, 5); // 重复次数

            System.out.println(Util.getNowTime() + ",send " + messageStr);

            // 7、发送消息
            producer.send(message);

            Thread.sleep(1000l);

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
