package com.kq.activemq.pubsub;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * MyListener
 *
 * @author kq
 * @date 2019-07-31
 */
public class MyListener implements MessageListener {

    public void onMessage(Message message) {
        try {
            System.out.println("订阅者一收到的消息："+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}