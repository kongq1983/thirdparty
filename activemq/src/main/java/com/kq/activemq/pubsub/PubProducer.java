package com.kq.activemq.pubsub;

import com.kq.activemq.Util;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * PubProducer
 *
 * @author kq
 * @date 2019-07-31
 */
public class PubProducer {

    public static void main(String[] args) throws Exception{

        //	第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号。
        String brokerURL = Util.ACTIVEMQ_URL;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);

        //	第二步：使用ConnectionFactory对象创建一个Connection对象。
        Connection connection = connectionFactory.createConnection();

        //	第三步：开启连接，调用Connection对象的start方法。
        connection.start();

        //	第四步：使用Connection对象创建一个Session对象。
        //	connection.createSession(transacted, acknowledgeMode)
        //	第一个参数transacted		是否开启事务，如果消息没发送成功，则重新发送，一般不开启
        //	第二个参数acknowledgeMode  如果开启事务，此参数无意义，如果不开启，此处为应答模式，自动应答，手动应答 一般自动应答
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //	第五步：使用Session对象创建一个Destination对象（topic、queue），此处创建一个Topic对象。
        //	参数：队列的名称。
        Topic topic = session.createTopic(SubConsumer.TOPIC);

        //	第六步：使用Session对象创建一个Producer对象。
        MessageProducer messageProducer = session.createProducer(topic);

        //	第七步：创建一个Message对象，创建一个TextMessage对象。
        //		TextMessage textMessage = new ActiveMQTextMessage();
        //		textMessage.setText("hello");

        String message = "pub-sub-message:"+System.currentTimeMillis();
        TextMessage textMessage = session.createTextMessage(message);

        System.out.println("pub-sub-message:"+message);

        //	第八步：使用Producer对象发送消息。
        messageProducer.send(textMessage);

        //	第九步：关闭资源。
        messageProducer.close();
        session.close();
        connection.close();


    }

}
