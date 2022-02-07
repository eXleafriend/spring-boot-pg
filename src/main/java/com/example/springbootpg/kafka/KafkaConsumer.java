package com.example.springbootpg.kafka;

import java.util.concurrent.CountDownLatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

	@Autowired
	private ObjectMapper objectMapper;

	private CountDownLatch latch = new CountDownLatch(1);

	private String payload = null;

	@KafkaListener(topics = "${test.topic}")
	public void receive(ConsumerRecord<?, ?> consumerRecord) throws JsonMappingException, JsonProcessingException {
		LOGGER.info("received payload='{}'", consumerRecord.toString());
		setPayload(consumerRecord.toString());

		final var json = consumerRecord.value().toString();
		final var des = objectMapper.readValue(json, Request.class);
		LOGGER.info("des: {} = {}", des.getClass(), des);

		latch.countDown();
	}

	public CountDownLatch getLatch() {
		return latch;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

}
