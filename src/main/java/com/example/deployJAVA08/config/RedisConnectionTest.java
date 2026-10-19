package com.example.deployJAVA08.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisConnectionTest implements ApplicationRunner {
    private final StringRedisTemplate redisTemplate;
    @Override
    public void run(ApplicationArguments args) {
        String key = "deployment:redis-test";

        redisTemplate.opsForValue()
                .set(key, "connected", java.time.Duration.ofMinutes(1));

        String value = redisTemplate.opsForValue().get(key);

        if ("connected".equals(value)) {
            System.out.println("REDIS_CONNECTION_TEST_SUCCESS");
        } else {
            throw new IllegalStateException(
                    "Redis test returned an unexpected value");
        }
    }
}
