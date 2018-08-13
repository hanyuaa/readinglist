package com.manning.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketMqConsumerConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RocketMqConsumerConfiguration.class);

    //@Autowired
    //private TaskExecutor taskExecutor;

    //@Autowired
    //private RocketMqProperties rocketMq;

    /**
     * Default mq push consumer default mq push consumer.
     *
     * @return the default mq push consumer
     *
     * @throws MQClientException the mq client exception
     */
    /*@Bean
    public DefaultMQPushConsumer defaultMQPushConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketMq.getConsumerGroup());
        consumer.setNamesrvAddr(rocketMq.getNamesrvAddr());
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        String[] strArray = AliyunMqTopicConstants.ConsumerTopics.OPT.split(GlobalConstant.Symbol.COMMA);
        for (String aStrArray : strArray) {
            String[] topicArray = aStrArray.split(GlobalConstant.Symbol.AT);
            String topic = topicArray[0];
            String tags = topicArray[1];
            if (PublicUtil.isEmpty(tags)) {
                tags = "*";
            }
            consumer.subscribe(topic, tags);
            log.info("RocketMq OpcPushConsumer topic = {}, tags={}", topic, tags);
        }

        consumer.registerMessageListener(optPushConsumer);
        consumer.setConsumeThreadMax(2);
        consumer.setConsumeThreadMin(2);

        taskExecutor.execute(() -> {
            try {
                Thread.sleep(5000);
                consumer.start();
                log.info("RocketMq PushConsumer OK.");
            } catch (InterruptedException | MQClientException e) {
                log.error("RocketMq PushConsumer, 出现异常={}", e.getMessage(), e);
            }
        });
        return consumer;
    }*/
}
