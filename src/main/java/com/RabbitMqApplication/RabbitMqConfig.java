package com.RabbitMqApplication;

/**
 * 
 */


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author kiran koli
 *
 * 
 */
@Configuration
public class RabbitMqConfig {

	@Value("${rabbitmq.username}")
	private String RabbitmqUsername;
//
	@Value("${rabbitmq.password}")
	private String RabbitmqPassword;
//

	@Value("${rabbitmq.virtualHost}")
	private String RabbitmqVirtualHost;

	@Value("${rabbitmq.host}")
	private String Rabbitmqhost;

	@Value("${rabbitmq.port}")
	private int RabbitmqPort;

	@Value("${rabbitmq.concurrentConsumers}")
	private int ConcurrentConsumers;

	@Value("${rabbitmq.MaxConcurrentConsumers}")
	private int MaxConcurrentConsumers;

	@Value("${rabbitmq.defaultRequeueRejected}")
	private boolean DefaultRequeueRejected;

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(Rabbitmqhost, RabbitmqPort);
		connectionFactory.setUsername(RabbitmqUsername);
		connectionFactory.setPassword(RabbitmqPassword);
		connectionFactory.setVirtualHost(RabbitmqVirtualHost);
		return connectionFactory;
	}

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,
			MessageConverter messageConverter) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(messageConverter);
		factory.setConcurrentConsumers(ConcurrentConsumers); // Adjust as needed
		factory.setMaxConcurrentConsumers(MaxConcurrentConsumers); // Adjust as needed
		factory.setDefaultRequeueRejected(DefaultRequeueRejected); // Don't requeue rejected messages by default

		/**
		 * factory.setStartConsumerMinInterval(2000L); // Set minimum start interval to 2 seconds 
		 * factory.setStopConsumerMinInterval(3000L); // Set minimum stopinterval to 3 seconds
		 */
		return factory;
	}

	@Bean
	public MessageConverter messageConverter() {
		return new SimpleMessageConverter();
	}

	// conctionFactory
	// RabbitTemplate
	// RabbitAdmin
	// All above config will do spring container for us

}
