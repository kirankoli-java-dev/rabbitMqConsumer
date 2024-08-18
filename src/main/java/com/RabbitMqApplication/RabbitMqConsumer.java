package com.RabbitMqApplication;

/**
 * 
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author kiran koli
 *
 * 
 */

@Service
public class RabbitMqConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqspringAplicationApplication.class);

	@RabbitListener(queues = { "${rabbitMq.queue.name}" })
	public void consume(String xml) {
		LOGGER.info("Received Message : --> " + xml);
		
	}

}
