package com.kq.activemq.acknowledge.queue;

import org.apache.commons.lang3.StringUtils;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * AcknowledgeQueueListener
 *
 * @author kq
 * @date 2019-08-01
 */
public class AcknowledgeQueueListener implements MessageListener {

    public void onMessage(Message message) {
        try {

//            System.out.println("Acknowledge 订阅者一收到的消息："+((TextMessage)message).getText());

            String text = ((TextMessage) message).getText();
            System.out.println(" Acknowledge 订阅者一收到的消息："+text );
            if (StringUtils.startsWith(text, "acknowledge")) {
                // Session.CLIENT_ACKNOWLEDGE 模式 , 需要调用acknowledge ,否则队列不会清除
                System.out.println(" acknowledge ");
                message.acknowledge();
            } else {
                //通知mq进行重发  最多重发六次  总共7次
                System.out.println(" 调用recover ");
//                session.recover();
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }




    }

}