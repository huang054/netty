package com.netty.dto;



public class ServerRespDataDTO  {
	
	private String userId;//用户手机号
	private String deviceId;
	private Long time = System.currentTimeMillis();
	private int index;
	private Iterable<AudioDTO>audioList;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Iterable<AudioDTO> getAudioList() {
		return audioList;
	}

	public void setAudioList(Iterable<AudioDTO> audioList) {
		this.audioList = audioList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
}
