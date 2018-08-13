package com.manning.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RocketMqProperties.class)
public class RocketMqProducerConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RocketMqProducerConfiguration.class);
    @Autowired
    private RocketMqProperties properties;

    /**
     * 初始化向rocketmq发送普通消息的生产者
     */
    @Bean
    @ConditionalOnProperty(prefix = RocketMqProperties.PREFIX, value = "producerInstanceName")
    public DefaultMQProducer defaultMQProducer() throws MQClientException {

        DefaultMQProducer producer = new DefaultMQProducer(properties.getProducerGroupName());
        producer.setNamesrvAddr(properties.getNamesrvAddr());
        producer.setInstanceName(properties.getProducerInstanceName());
        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(10);

        producer.start();
        log.info("RocketMq defaultProducer Started.");
        return producer;
    }
}
