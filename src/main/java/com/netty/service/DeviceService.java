package com.netty.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.netty.dao.DeviceDAO;
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
	
	@Autowired
	private DeviceDAO deviceDao;
	
	public DeviceModel findByDeviceId(String deviceId) {
		return deviceDao.findByDeviceId(deviceId);
	}
}
