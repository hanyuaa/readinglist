package com.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * Created by hanyu on 2018/9/7.
 */
public class BroadcastConsumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group_name");
        consumer.setNamesrvAddr("192.168.172.3:9876");
        //consumer.setNamesrvAddr("10.66.206.232:9876");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        //set to broadcast mode
        consumer.setMessageModel(MessageModel.BROADCASTING);

        //consumer.subscribe("TopicTest", "TagA || TagC || TagD || TagB ||TagE");
        consumer.subscribe("TopicTest", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                //System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
                try {
                    byte[] body = msgs.get(0).getBody();
                    String s = new String(body, RemotingHelper.DEFAULT_CHARSET);
                    System.out.println(s);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.printf("Broadcast Consumer Started.%n");
    }
}
