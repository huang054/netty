//package com.netty.dto;
//
//import javax.validation.constraints.NotBlank;
//
//public class CmdDTO {
//	
//	@NotBlank(message="deviceId不能为空")
//	private String deviceId;
//	
//	@NotBlank(message="userId不能为空")
//	private String userId;
//	
//	@NotBlank(message="cmd不能为空")
//	private String cmdJson;//这个用来解析成不同的DTO
//	
//	public String getBindingKey() {
//		return userId+deviceId;
//	}
//	
//	public String getDeviceId() {
//		return deviceId;
//	}
//
//	public void setDeviceId(String deviceId) {
//		this.deviceId = deviceId;
//	}
//
//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//
//	public String getCmdJson() {
//		return cmdJson;
//	}
//
//	public void setCmdJson(String cmdJson) {
//		this.cmdJson = cmdJson;
//	}
//
//	
//	
//	
//	
//}
