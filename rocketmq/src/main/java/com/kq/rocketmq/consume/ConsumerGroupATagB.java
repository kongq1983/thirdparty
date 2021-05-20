package com.kq.rocketmq.consume;

import com.kq.rocketmq.constant.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @author kq
 * @date 2020-12-29 9:48
 * @since 2020-0630
 */
public class ConsumerGroupATagB {

    public static void main(String[] args) throws InterruptedException, MQClientException {

        String groupName = Constants.GROUP_A;
        String tag = Constants.TAG_B;
//        String tag = Constants.TAG_C;
        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);

        //广播模式 集群模式
        consumer.setMessageModel(MessageModel.BROADCASTING);

        // Specify name server addresses.
        consumer.setNamesrvAddr(Constants.DEFAULT_NAME_SERVER);

        // Subscribe one more more topics to consume.
        consumer.subscribe("TopicTest", tag);
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {


                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);

                for(MessageExt m : msgs) {
                    System.out.println("message="+new String(m.getBody()));
                }

                System.out.println();

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }

}
