package com.petshopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class PetshopAdminApiApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value(value = "${spring.config.activate.on-profile}")
	private String activeProfile;

	@Value(value = "${redis.host}")
	private String redisHost;

	@Value(value = "${redis.port}")
	private int redisPort;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PetshopAdminApiApplication.class);
		app.run();
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		return objectMapper.registerModule(javaTimeModule);
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisHost, redisPort);
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory(config);
		return jedisConFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

	@Override
	public void run(String... args) {
		logger.info("#############################################################");
		logger.info("active profile: {}", activeProfile);
		logger.info("redisHost: {}", redisHost);
		logger.info("redisPort: {}", redisPort);
		logger.info("#############################################################");
	}

}
