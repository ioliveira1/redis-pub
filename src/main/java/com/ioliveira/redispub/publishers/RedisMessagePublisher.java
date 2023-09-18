package com.ioliveira.redispub.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisMessagePublisher implements MessagePublisher {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public RedisMessagePublisher() {
    }

    public RedisMessagePublisher(final StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void publish(final String channel, final String message) {
        redisTemplate.convertAndSend(channel, message);
    }
}
