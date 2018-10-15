package com.netty.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.netty.cache.ConcurrentHashMapCache;
import com.netty.cache.Producer;

import io.netty.channel.Channel;

@Component
public class RedisDiscussService {
	
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    
   private static RedisDiscussService self  ;
	
	private RedisDiscussService() {
		self = this;
	}
	
	public static RedisDiscussService getInstance() {
		return self;
	}
    public void delete(String token) {
    	redisTemplate.delete(token);
    }
    public String get(String token) {
    	return redisTemplate.opsForValue().get(token);
    }
   
    public void save(String token,String ch) {
    	redisTemplate.opsForValue().set(token, ch, 6*60, TimeUnit.SECONDS);
    
    	
    }
}
