package com.netty.service;

import org.springframework.stereotype.Service;

import com.netty.model.DeviceModel;

@Service
public class DeviceService extends BaseService<DeviceModel> {
	
	private static DeviceService self  ;
	
	private DeviceService() {
		self = this;
	}
	
	public static DeviceService getInstance() {
		return self;
	}
}
