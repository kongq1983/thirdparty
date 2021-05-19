package com.kq.rocketmq.producer;

import com.kq.rocketmq.constant.Constants;
import com.kq.rocketmq.util.MyByteUtil;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 假如group_a和group_b都订阅了topic1，现在往topic1里发了一条消息，group_a和group_b是都能消费这条消息，还是只能有一个能消费这条消息。答案应该是两个组都能消费到这条消息
 * @author kq
 * @date 2020-12-25 10:26
 * @since 2020-0630
 */
public class SyncProducer {
    // https://www.cnblogs.com/zhangyjblogs/p/14163380.html
//    https://zhuanlan.zhihu.com/p/159017211
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        String groupName = Constants.GROUP_A;
//        String tag = Constants.TAG_A;
        String tag = Constants.TAG_B;
//        String tag = Constants.TAG_C;
//        String tag = Constants.TAG_A;
        DefaultMQProducer producer = new
                DefaultMQProducer(groupName);
        // Specify name server addresses.
//        producer.setNamesrvAddr("localhost:9876");
        producer.setNamesrvAddr(Constants.DEFAULT_NAME_SERVER);
//        producer.setClientIP("172.16.67.21");
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 5; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest" /* Topic */,
                    tag /* Tag */,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
//            SendResult sendResult = producer.send(msg);
            // 会报Exception in thread "main" org.apache.rocketmq.remoting.exception.RemotingTooMuchRequestException: sendDefaultImpl call timeout
            // 指定100000
            MyByteUtil.print(msg.getBody());
            SendResult sendResult = producer.send(msg,100000);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use. broadcasting
        producer.shutdown();
    }

}
