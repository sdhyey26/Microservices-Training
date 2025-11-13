package com.tss.kafka_demo.Kafka;

import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.tss.kafka_demo.entity.UserEntity;


@Service
public class KafkaProducer {

	private KafkaTemplate<String, UserEntity> template;
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(KafkaProducer.class);

	public KafkaProducer(KafkaTemplate<String, UserEntity> template) {
		super();
		this.template = template;
	}
	
	public void sendMessage(UserEntity user) {
		logger.info("user profile submitted ---------------- > {}", user);
		template.send("myTopic" , user);
	}
}
