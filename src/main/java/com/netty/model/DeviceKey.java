//package com.netty.model;
//
//import java.io.Serializable;
//
//public class DeviceKey implements Serializable{
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	public DeviceKey() {}
//	public DeviceKey(String userId,String deviceId) {
//		this.userId = userId;
//		this.deviceId = deviceId;
//	}
//	
//	private String userId;//用户手机号
//	private String deviceId;
//	
//	public  String getKey() {
//		return deviceId+userId;
//	}
//	
//	public String getUserId() {
//		return userId;
//	}
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//	public String getDeviceId() {
//		return deviceId;
//	}
//	public void setDeviceId(String deviceId) {
//		this.deviceId = deviceId;
//	}
//	
//}
