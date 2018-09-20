package com.netty.common;

import com.alibaba.fastjson.JSON;
import com.netty.dto.StdRespDTO;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public class ReturnUtils {
	public static void writeAndFlush(StdRespDTO respDTO,Channel channel) {
		if(channel==null || channel.isActive()==false)return;
		String jsonStr = JSON.toJSONString(respDTO);
		ByteBuf resp = channel.alloc().buffer(jsonStr.getBytes().length+10);
	    resp.writeCharSequence(jsonStr+"\n", io.netty.util.CharsetUtil.UTF_8);
	    channel.writeAndFlush(resp);
	}
}
