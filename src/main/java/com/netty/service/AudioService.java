package com.netty.service;

import org.springframework.stereotype.Service;

import com.netty.model.AudioModel;

@Service
public class AudioService extends BaseService<AudioModel>{
	private static AudioService self  ;
	private AudioService() {
		self = this;
	}
	public static AudioService getInstance() {
		return self;
	}
}
