package com.manning.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties(RocketMqProperties.class)
@ConditionalOnProperty(prefix = RocketMqProperties.PREFIX, value = "namesrvAddr")
public class RocketMqAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(RocketMqAutoConfiguration.class);
    @Autowired
    private RocketMqProperties properties;
    @Autowired
    private ApplicationEventPublisher publisher;

    private static boolean isFirstSub = true;

    private static long startTime = System.currentTimeMillis();

    /**
     * 初始化rocketmq消息监听方式的消费者
     */
    @Bean
    @ConditionalOnProperty(prefix = RocketMqProperties.PREFIX, value = "consumerInstanceName")
    public DefaultMQPushConsumer pushConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(properties.getConsumerGroupName());
        consumer.setNamesrvAddr(properties.getNamesrvAddr());
        consumer.setInstanceName(properties.getConsumerInstanceName());
        if (properties.isConsumerBroadcasting()) {
            consumer.setMessageModel(MessageModel.BROADCASTING);
        }
        consumer.setConsumeMessageBatchMaxSize(
                properties.getConsumerBatchMaxSize() == 0 ? 1 : properties.getConsumerBatchMaxSize());// 设置批量消费，以提升消费吞吐量，默认是1
        /**
         * 订阅指定topic下tags
         */
        List<String> subscribeList = properties.getSubscribe();
        for (String sunscribe : subscribeList) {
            consumer.subscribe(sunscribe.split(":")[0], sunscribe.split(":")[1]);
        }

        if (properties.isEnableOrderConsumer()) {
            consumer.registerMessageListener((List<MessageExt> msgs, ConsumeOrderlyContext context) -> {
                try {
                    context.setAutoCommit(true);
                    msgs = filter(msgs);
                    if (msgs.size() == 0) return ConsumeOrderlyStatus.SUCCESS;
                    this.publisher.publishEvent(new RocketmqEvent(msgs, consumer));
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
                // 如果没有return success，consumer会重复消费此信息，直到success。
                return ConsumeOrderlyStatus.SUCCESS;
            });
        } else {
            consumer.registerMessageListener((List<MessageExt> msgs, ConsumeConcurrentlyContext context) -> {
                try {
                    msgs = filter(msgs);
                    if (msgs.size() == 0) return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    this.publisher.publishEvent(new RocketmqEvent(msgs, consumer));
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                // 如果没有return success，consumer会重复消费此信息，直到success。
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);// 延迟5秒再启动，主要是等待spring事件监听相关程序初始化完成，否则，回出现对RocketMQ的消息进行消费后立即发布消息到达的事件，然而此事件的监听程序还未初始化，从而造成消息的丢失
                    /**
                     * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
                     */
                    try {
                        consumer.start();
                    } catch (Exception e) {
                        log.info("RocketMq pushConsumer Start failure!!!.");
                        log.error(e.getMessage(), e);
                    }
                    log.info("RocketMq pushConsumer Started.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        return consumer;
    }

    private List<MessageExt> filter(List<MessageExt> msgs) {
        if (isFirstSub && !properties.isEnableHisConsumer()) {
            msgs = msgs.stream().filter(item -> startTime - item.getBornTimestamp() < 0).collect(Collectors.toList());
        }
        if (isFirstSub && msgs.size() > 0) {
            isFirstSub = false;
        }
        return msgs;
    }
}