package org.wolf.common.config.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);
	private String host;
	private int port;
	private int timeout;
	private int maxIdle;
	private long maxWaitMillis;

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public void setMaxWaitMillis(long maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	@Bean("jedisPool")
	public JedisPool getJedisPool() {
		logger.info("RedisConfiguration::JedisPool注入成功！！");
		logger.info("redis地址：" + host + ":" + port);
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port,
				timeout);
		return jedisPool;
	}

	@Bean
	public JedisConnectionFactory getConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		factory.setPoolConfig(jedisPoolConfig);
		factory.setPort(port);
		factory.setHostName(host);
		factory.setTimeout(timeout);
		logger.info("JedisConnectionFactory bean init success.");
		return factory;
	}

	@Bean("redisTemplate")
	public RedisTemplate getRedisTemplate() {
		RedisTemplate template = new StringRedisTemplate(getConnectionFactory());
		return template;
	}
}
