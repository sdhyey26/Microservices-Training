package com.tss.kafka_demo.Kafka;

import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.tss.kafka_demo.entity.UserEntity;

@Service
public class KafkaConsumer {
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(KafkaConsumer.class);
	
	UserEntity user = new UserEntity();
	
	@KafkaListener(topics = "myTopic" , groupId = "myGroup")
	public void consume (UserEntity user) {
		logger.info("First name --------> " + user.getFirstName());
		logger.info("Last name --------> " + user.getLastName());
		logger.info("Last email --------> " + user.getEmail());
	}

}
