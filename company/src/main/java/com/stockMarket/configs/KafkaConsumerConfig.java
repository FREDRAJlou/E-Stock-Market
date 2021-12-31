//package com.flightBooking.configs;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import com.flightBooking.entities.Booking;
//
//@Profile({"dev"})
//@EnableKafka
//@Configuration
//public class KafkaConsumerConfig {
//
//	@Bean
//	public ConsumerFactory<String, Booking> userConsumerFactory() {
//		Map<String, Object> config = new HashMap<>();
//
//		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
//		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//
//		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
//				new JsonDeserializer<>(Booking.class));
//	}
//
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String, Booking> userKafkaListenerFactory() {
//		ConcurrentKafkaListenerContainerFactory<String, Booking> factory = new ConcurrentKafkaListenerContainerFactory<>();
//		factory.setConsumerFactory(userConsumerFactory());
//		return factory;
//	}
//
//}
