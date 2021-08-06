package com.poc.mqm.producer;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import com.poc.mqm.domain.CustomTextMessage;
import com.poc.mqm.utils.Constants;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Producer2 implements CommandLineRunner{

	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public void run(String... args) throws Exception {
		log.info("producer2 started");
		IntStream.range(0, 700).forEach(data -> {
			CustomTextMessage customTextMessage = new CustomTextMessage(null, "queueBmessage" + data);
			MessagePostProcessor messagePostProcessor = (msg) -> {
				msg.setStringProperty("_type", "com.mq.poc.model.CustomTextMessage");
				return msg;
			};
			jmsTemplate.convertAndSend(Constants.QUEUEB, customTextMessage,messagePostProcessor);
		});
		log.info("producer2 completed");
	}
	
	
}
