package com.example.springbootpg.kafka;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@Slf4j
public class EmbeddedKafkaIntegrationTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private KafkaConsumer consumer;

	@Autowired
	private KafkaProducer producer;

	@Value("${test.topic}")
	private String topic;

	@Before
	public void setUp() {
		objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(),
				ObjectMapper.DefaultTyping.NON_FINAL, As.PROPERTY);
	}

	@Test
	public void givenEmbeddedKafkaBroker_whenSendingtoSimpleProducer_thenMessageReceived()
			throws Exception {
		// producer.send(topic, "Sending with own simple KafkaProducer");
		final var request = new StorageRequest("warpcms", "TN-00001");
		producer.send(topic, request);
		final var start = System.currentTimeMillis();
		consumer.getLatch().await(100000, TimeUnit.MILLISECONDS);
		final var end = System.currentTimeMillis();
		log.info("Time taken: {}s", (end - start) / 1000.0);

		assertThat(consumer.getLatch().getCount(), equalTo(0L));
		assertThat(consumer.getPayload(), containsString("embedded-test-topic"));
	}
}
