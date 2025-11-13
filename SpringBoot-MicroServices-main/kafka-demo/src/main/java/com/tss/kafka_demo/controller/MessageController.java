package com.tss.kafka_demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tss.kafka_demo.Kafka.KafkaProducer;
import com.tss.kafka_demo.entity.UserEntity;

@RestController
@RequestMapping("api/kafka")
public class MessageController {

	private KafkaProducer producer;
	UserEntity user = new UserEntity();
	
	public MessageController(KafkaProducer producer) {
		this.producer = producer;
	}
	
	@PostMapping("/message")
	public String publish(@RequestBody UserEntity user){
		producer.sendMessage(user);
		
		return user.toString();
	}
}
