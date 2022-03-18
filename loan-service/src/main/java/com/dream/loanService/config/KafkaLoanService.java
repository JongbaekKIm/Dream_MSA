package com.dream.loanService.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.dream.loanService.domain.Message;

//KafkaProducer
@EnableKafka
@Configuration
public class KafkaLoanService {

	@Value("${kafka.server_endpoint}") // producer, consumer 둘다 필요
	private String kafkaServerEndpoint;

	@Value(value = "{kafka.group_id}") // consumer 필요
	private String kafkaGroupId;

	// producer
	@Bean
	public ProducerFactory<String, Message> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerEndpoint);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	public KafkaTemplate<String, Message> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	// ----------------------------------------
	
	// consumer
	@Bean
	public ConsumerFactory<String, String> consumerFactory() {

		Map<String, Object> map = new HashMap<>();

		map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerEndpoint);
		map.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
		map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

		return new DefaultKafkaConsumerFactory<>(map);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListner(){
		
		ConcurrentKafkaListenerContainerFactory<String, String> obj = new ConcurrentKafkaListenerContainerFactory<>();
		obj.setConsumerFactory(consumerFactory());
		return obj;
	}
}
