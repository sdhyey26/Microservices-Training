package com.tss.kafka_demo.Kafka;

import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducer {

	private KafkaTemplate<String, String> template;
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(KafkaProducer.class);

	public KafkaProducer(KafkaTemplate<String, String> template) {
		super();
		this.template = template;
	}
	
	public void sendMessage(String message) {
		logger.info("Message published ---------------- > " , message);
		template.send("myTopic" , message);
	}
}
