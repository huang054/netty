package com.netty.cache;

import io.netty.channel.Channel;

public interface ChannelCache {
	
	public Channel get(String key);

	public void put(String key, Channel channel);

	public void remove(String key);
}
