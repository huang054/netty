package com.netty.cache;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.netty.controller.RemoteController;

import io.netty.channel.Channel;


@Service
public class ConcurrentHashMapCache implements ChannelCache {
	private static final Logger logger = LoggerFactory.getLogger(ConcurrentHashMapCache.class);
	
	private static ConcurrentHashMapCache self;
	
	private  int capacity = 100000;
	
	private Map<String,Channel> deviceIdChannelCache 			=  new ConcurrentHashMap<String,Channel>(capacity);
	private Map<String,String>  channelDeviceIdCache 			=  new ConcurrentHashMap<String,String>(capacity);
	private Map<String,String> deviceIdUserIdBindingCache    =  new ConcurrentHashMap<String,String>(capacity);
	
	public static ConcurrentHashMapCache getInstance() {
		return self;
	}
	
	@PostConstruct
	private void selfInit() {
		self=this;
		System.out.println("selfInit:"+ConcurrentHashMapCache.getInstance());
	}
	
	public Channel get(String key) {
		return deviceIdChannelCache.get(key);
	}
	
	public void put(String deviceId, Channel channel) {
		if(deviceIdChannelCache.containsKey(deviceId)) {return;}
		deviceIdChannelCache.put(deviceId, channel);
		channelDeviceIdCache.put(channel.id().asLongText(), deviceId);
	    logger.info("put deviceIdChannelCache length:"+deviceIdChannelCache.size()+" deviceIdChannelCache:"+deviceIdChannelCache.toString());
	    logger.info("put keyCache size:"+channelDeviceIdCache.size()+" keyCache:"+channelDeviceIdCache.toString());
		System.out.println("keyCache size:"+channelDeviceIdCache.size());
		System.out.println("deviceIdChannelCache size:"+deviceIdChannelCache.size());
	}

	public void remove(String key) {
		deviceIdChannelCache.remove(key);
	}
	
	public void removeByChannelId(String channelId) {
		String deviceId = channelDeviceIdCache.get(channelId);
		if(null==deviceId)return;
		deviceIdChannelCache.remove(deviceId);
		channelDeviceIdCache.remove(channelId);
		   logger.info(" remove deviceIdChannelCache length:"+deviceIdChannelCache.size()+" deviceIdChannelCache:"+deviceIdChannelCache.toString());
		    logger.info("remove keyCache size:"+channelDeviceIdCache.size()+" keyCache:"+channelDeviceIdCache.toString());
		System.out.println("keyCache size:"+channelDeviceIdCache.size());
		System.out.println("deviceIdChannelCache size:"+deviceIdChannelCache.size());
	}

	public Map<String, String> getDeviceIdUserIdBindingCache() {
		return deviceIdUserIdBindingCache;
	}

	public void setDeviceIdUserIdBindingCache(Map<String, String> deviceIdUserIdBindingCache) {
		this.deviceIdUserIdBindingCache = deviceIdUserIdBindingCache;
	}

//	@Override
//	public void put(String key, Channel channel) {
//		// TODO Auto-generated method stub
//		
//	}

	
	
}
